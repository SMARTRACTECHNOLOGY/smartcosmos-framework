package net.smartcosmos.platform.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.setup.Environment;
import net.smartcosmos.platform.api.ICosmosContextBootstrap;
import net.smartcosmos.platform.api.config.ICosmosConfiguration;
import net.smartcosmos.platform.api.dao.ICosmosDAOFactory;
import net.smartcosmos.platform.api.service.ICosmosServiceFactory;
import net.smartcosmos.platform.bundle.quartz.IQuartzJobDefinition;
import org.apache.http.client.HttpClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

public class AbstractCosmosContext<S extends ICosmosConfiguration,
        T extends ICosmosDAOFactory,
        U extends ICosmosServiceFactory> implements ICosmosContextBootstrap<S, T, U>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCosmosContext.class);

    private S configuration;

    private T daoFactory;

    private U serviceFactory;

    private SessionFactory sessionFactory;

    private Environment environment;

    private Scheduler scheduler;

    private HttpClient httpClient;

    @Override
    public S getConfiguration()
    {
        return configuration;
    }

    @Override
    public T getDAOFactory()
    {
        return daoFactory;
    }

    @Override
    public U getServiceFactory()
    {
        return serviceFactory;
    }

    @Override
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Override
    public Environment getEnvironment()
    {
        return environment;
    }

    @Override
    public Scheduler getScheduler()
    {
        return scheduler;
    }

    @Override
    public HttpClient getHttpClient()
    {
        return httpClient;
    }

    @VisibleForTesting
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String getName()
    {
        return "Context Job Listener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobContext)
    {
        LOG.info(jobContext.getJobDetail().getKey().getName() + " :: " +
                jobContext.getJobDetail().getKey().getGroup() + " is about to execute...");

        try
        {
            final UnitOfWork unitOfWork = jobContext
                    .getJobInstance()
                    .getClass()
                    .getMethod("execute", JobExecutionContext.class)
                    .getAnnotation(UnitOfWork.class);

            if (unitOfWork != null)
            {
                final Session session = sessionFactory.openSession();
                jobContext.put(Session.class, session);
                jobContext.put(UnitOfWork.class, unitOfWork);
                configureSession(session, unitOfWork);
                ManagedSessionContext.bind(session);
                beginTransaction(session, unitOfWork);
            }
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }

        jobContext.put(IQuartzJobDefinition.SMART_COSMOS_CONTEXT, this);
    }

    private void beginTransaction(Session session, UnitOfWork unitOfWork)
    {
        if (unitOfWork.transactional())
        {
            session.beginTransaction();
        }
    }

    private void configureSession(Session session, UnitOfWork unitOfWork)
    {
        session.setDefaultReadOnly(unitOfWork.readOnly());
        session.setCacheMode(unitOfWork.cacheMode());
        session.setFlushMode(unitOfWork.flushMode());
    }

    private void rollbackTransaction(Session session, UnitOfWork unitOfWork)
    {
        if (unitOfWork.transactional())
        {
            final Transaction txn = session.getTransaction();
            if (txn != null && txn.isActive())
            {
                txn.rollback();
            }
        }
    }

    private void commitTransaction(Session session, UnitOfWork unitOfWork)
    {
        if (unitOfWork.transactional())
        {
            final Transaction txn = session.getTransaction();
            if (txn != null && txn.isActive())
            {
                txn.commit();
            }
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context)
    {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException)
    {
        Session session = (Session) context.get(Session.class);
        try
        {
            UnitOfWork unitOfWork = (UnitOfWork) context.get(UnitOfWork.class);

            if (jobException != null)
            {
                if (session != null)
                {
                    rollbackTransaction(session, unitOfWork);
                }
                LOG.warn(context.getJobDetail().getKey().getName() + " :: " +
                        context.getJobDetail().getKey().getGroup() + " threw exception! Exception Message: " +
                        jobException.getMessage());
            } else
            {
                if (session != null)
                {
                    commitTransaction(session, unitOfWork);
                }
                LOG.info(context.getJobDetail().getKey().getName() + " :: " +
                        context.getJobDetail().getKey().getGroup() + " executed");
            }
        } finally
        {
            if (null != session)
            {
                session.close();
                ManagedSessionContext.unbind(sessionFactory);
            }
        }
    }

    //
    // Bootstrap Methods
    //


    @Override
    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    @Override
    public void setConfiguration(S configuration)
    {
        this.configuration = configuration;
    }

    @Override
    public void setDAOFactory(T daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void setServiceFactory(U serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void setHttpClient(HttpClient httpClient)
    {
        this.httpClient = httpClient;
    }

    @Override
    public void setScheduler(Scheduler scheduler)
    {
        this.scheduler = scheduler;

        try
        {
            scheduler.getListenerManager().addJobListener(this, allJobs());
        } catch (SchedulerException e)
        {
            LOG.warn("Unable to add job listener to scheduler: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
