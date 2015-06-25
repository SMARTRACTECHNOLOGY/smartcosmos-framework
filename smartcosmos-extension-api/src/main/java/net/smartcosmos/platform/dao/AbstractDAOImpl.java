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

import java.util.ArrayList;
import java.util.Collection;

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.IBaseDAO;
import net.smartcosmos.platform.api.dao.IPageProvider;
import net.smartcosmos.platform.api.dao.domain.IPage;
import net.smartcosmos.platform.dao.domain.PageEntry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public abstract class AbstractDAOImpl<S extends IDomainResource, T extends S> extends AbstractDAO<T> implements
        IBaseDAO<S>, IPageProvider<S>
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

    @Override
    public S upsert(S object)
    {
        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        S findResult = null;

        if (null != object.getUrn())
        {
            findResult = findByUrn(classInstance, object.getUrn());
        }

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

    /*
     * (non-Javadoc)
     * 
     * @see net.smartcosmos.platform.dao.IPageProvider#count()
     */
    @Override
    public Long count()
    {
        final Criteria criteriaCount = criteria();
        criteriaCount.setProjection(Projections.rowCount());

        Object result = criteriaCount.uniqueResult();
        if (result == null)
        {
            return 0L;
        } else
        {
            return (Long) criteriaCount.uniqueResult();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.smartcosmos.platform.dao.IPageProvider#page(int, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public IPage<S> page(int page, int pageSize)
    {
        Collection<S> list = new ArrayList<S>();

        final int totalSize = count().intValue();
        final int totalPages = totalSize / pageSize;
        final int currentPage = pageSize * page;

        Criteria criteria = criteria();
        criteria.setFirstResult(page * pageSize);
        criteria.setMaxResults(pageSize);

        for (Object o : criteria.list())
        {
            list.add((S) o);
        }

        IPage<S> pagination = new PageEntry<S>(list, totalPages, totalSize, currentPage, pageSize);

        return pagination;
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
            LOG.warn("Unable to instantiate object of type {}", classInstance.getName());
            LOG.debug(e.getMessage(), e);
        } catch (IllegalAccessException e)
        {
            LOG.debug(e.getMessage(), e);
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
                LOG.warn("Unable to locate object of type {} with unique ID {}", classInstance.getName(),
                        object.getUrn());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public S update(S object)
    {
        T instance = null;

        try
        {
            instance = classInstance.newInstance();
            instance.copy(object);

            instance = (T) currentSession().merge(instance);

        } catch (InstantiationException e)
        {
            LOG.warn("Unable to instantiate object of type {}", classInstance.getName());
            LOG.debug(e.getMessage(), e);
        } catch (IllegalAccessException e)
        {
            LOG.debug(e.getMessage(), e);
        }
        return (S) instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(Class<?> clazz, String urn, IAccount account)
    {
        Preconditions.checkNotNull(account, "Parameter 'account' must not be null");
        S object = null;

        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("urn", UUID.fromString(urn)));
        criteria.add(Restrictions.eq("accountUrn", UUID.fromString(account.getUrn())));
        object = (S) criteria.uniqueResult();

        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(Class<?> clazz, String urn)
    {
        if (urn == null)
        {
            return null;
        }

        S object = null;

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query query = currentSession().createQuery("select e from " + entityName + " e where e.urn = :urn")
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
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
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
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession()
                .createQuery("select m from " + entityName + " m where m.account.urn = :urn and m.moniker = :moniker")
                .setParameter("urn", UUID.fromString(account.getUrn())).setParameter("moniker", monikerEquals);

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
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession()
                .createQuery(
                        "select m from " + entityName + " m where m.account.urn = :urn and m.moniker like :moniker")
                .setParameter("urn", UUID.fromString(account.getUrn())).setParameter("moniker", monikerLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((IDomainResource) o);
        }

        return list;
    }
}
