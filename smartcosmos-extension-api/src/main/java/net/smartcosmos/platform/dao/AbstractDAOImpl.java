package net.smartcosmos.platform.dao;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import com.google.common.base.Preconditions;
import io.dropwizard.hibernate.AbstractDAO;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.IBaseDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public abstract class AbstractDAOImpl<S extends IDomainResource, T extends S>
        extends AbstractDAO<T> implements IBaseDAO<S>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOImpl.class);

    protected final boolean canDelete;

    protected final Class<T> classInstance;

    protected AbstractDAOImpl(Class<T> classInstance, SessionFactory sessionFactory)
    {
        this(classInstance, sessionFactory, false);
    }

    protected AbstractDAOImpl(Class<T> classInstance, SessionFactory sessionFactory, boolean canDelete)
    {
        super(sessionFactory);
        this.canDelete = canDelete;
        this.classInstance = classInstance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public S upsert(S object)
    {
        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        S findResult = findByUrn(classInstance, object.getUrn());

        if (null != findResult)
        {

            update(object);

            return object;
        } else
        {
            // INSERT
            return insert(object);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public S insert(S object)
    {
        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        T instance = null;
        try
        {
            instance = classInstance.newInstance();
            instance.copy(object);

            instance = persist(instance);

        } catch (InstantiationException e)
        {
            LOG.warn("Unable to instantiate object of type " + classInstance.getName());
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return (S) instance;
    }

    @Override
    public boolean canDelete()
    {
        return canDelete;
    }

    @Override
    public void delete(S object)
    {
        if (!canDelete)
        {
            LOG.warn("Attempt to delete an immutable object detected");
            throw new UnsupportedOperationException("Type does not support deletion");
        } else
        {

            S instance = findByUrn(classInstance, object.getUrn());

            if (null != instance)
            {
                currentSession().delete(instance);
            } else
            {
                LOG.warn("Unable to locate object of type " + classInstance.getName() +
                        " with system urn " + object.getUrn());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(S object)
    {
        T instance;

        try
        {
            instance = classInstance.newInstance();
            instance.copy(object);

            currentSession().merge(instance);

        } catch (InstantiationException e)
        {
            LOG.warn("Unable to instantiate object of type " + classInstance.getName());
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(Class<?> clazz, String urn, IAccount account)
    {
        Preconditions.checkNotNull(account, "Parameter 'account' must not be null");
        S object = null;

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
         * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
         * identifier.
         *
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query query = currentSession()
                .createQuery("select e from " + entityName +
                        " e where e.account.urn = :accountUrn and e.urn = :urn")
                .setParameter("accountUrn", UUID.fromString(account.getUrn()))
                .setParameter("urn", UUID.fromString(urn));

        object = (S) query.uniqueResult();

        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(Class<?> clazz, String urn)
    {
        S object = null;

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
         * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
         * identifier.
         *
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query query = currentSession()
                .createQuery("select e from " + entityName +
                        " e where e.urn = :urn")
                .setParameter("urn", UUID.fromString(urn));

        object = (S) query.uniqueResult();

        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<S> findByAccount(Class<?> clazz, IAccount account)
    {
        Collection<S> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
         * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
         * identifier.
         *
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession().createQuery("select m from " + entityName + " m where m.account.urn = :urn")
                .setParameter("urn", UUID.fromString(account.getUrn()));

        for (Object o : listQuery.list())
        {
            list.add((S) o);
        }

        return list;
    }

    @Override
    public Collection<IDomainResource> searchByMoniker(Class<?> clazz, String monikerEquals, IAccount account)
    {
        Collection<IDomainResource> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
         * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
         * identifier.
         *
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession().createQuery("select m from " + entityName +
                " m where m.account.urn = :urn and m.moniker = :moniker")
                .setParameter("urn", UUID.fromString(account.getUrn()))
                .setParameter("moniker", monikerEquals);

        for (Object o : listQuery.list())
        {
            list.add((IDomainResource) o);
        }

        return list;
    }

    @Override
    public Collection<IDomainResource> searchByMonikerLike(Class<?> clazz, String monikerLike, IAccount account)
    {
        Collection<IDomainResource> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
         * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
         * identifier.
         *
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession().createQuery("select m from " + entityName +
                " m where m.account.urn = :urn and m.moniker like :moniker")
                .setParameter("urn", UUID.fromString(account.getUrn()))
                .setParameter("moniker", monikerLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((IDomainResource) o);
        }

        return list;
    }
}

