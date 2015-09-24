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
package net.smartcosmos.platform.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.hibernate.HibernateQuery;

import io.dropwizard.hibernate.AbstractDAO;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.IAdvancedQuery;
import net.smartcosmos.platform.api.dao.IBaseDAO;
import net.smartcosmos.platform.api.dao.IPageProvider;
import net.smartcosmos.platform.api.dao.domain.IPage;
import net.smartcosmos.platform.dao.domain.PageEntry;
import net.smartcosmos.util.UuidUtil;

public abstract class AbstractDAOImpl<S extends IDomainResource<S>, T extends S> extends AbstractDAO<T>implements
        IBaseDAO<S>, IPageProvider<S>, IAdvancedQuery<S>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOImpl.class);

    protected final boolean canDelete;
    private final Class<T> entityClass;

    protected AbstractDAOImpl(final Class<T> classInstance, final SessionFactory sessionFactory)
    {
        this(classInstance, sessionFactory, false);
    }

    protected AbstractDAOImpl(final Class<T> classInstance, final SessionFactory sessionFactory,
            final boolean canDelete)
    {
        super(sessionFactory);
        this.entityClass = classInstance;
        this.canDelete = canDelete;
    }

    /**
     * Returns the entity class managed by this DAO.
     *
     * @return the entity class managed by this DAO
     */
    @Override
    public Class<T> getEntityClass()
    {
        return (Class<T>) entityClass;
    }

    @Override
    public S upsert(final S object)
    {
        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        S findResult = null;

        if (null != object.getUrn())
        {
            findResult = findByUrn(getEntityClass(), object.getUrn());
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
    public IPage<S> page(final int page, final int pageSize)
    {
        if (page < 1)
        {
            throw new IllegalArgumentException("Pages start at 1.");
        }

        if (pageSize < 1)
        {
            throw new IllegalArgumentException("Page must contain at least 1 entry.");
        }

        Collection<S> list = new ArrayList<S>();

        final int totalSize = count().intValue();
        final int totalPages = Double.valueOf(Math.ceil(Double.valueOf(totalSize) / Double.valueOf(pageSize)))
                .intValue();

        Criteria criteria = criteria();
        criteria.setFirstResult((page - 1) * pageSize);
        criteria.setMaxResults(pageSize);

        for (Object o : criteria.list())
        {
            list.add((S) o);
        }

        IPage<S> pagination = new PageEntry<S>(list, totalPages, totalSize, page, pageSize);

        return pagination;
    }

    @Override
    public S insert(final S object)
    {
        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        T instance = null;
        try
        {
            instance = getEntityClass().newInstance();
            instance.copy(object);

            instance = persist(instance);

        } catch (InstantiationException e)
        {
            LOG.warn("Unable to instantiate object of type {}", getEntityClass().getName());
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
    public void delete(final S object)
    {
        if (!canDelete)
        {
            LOG.warn("Attempt to delete an immutable object detected");
            throw new UnsupportedOperationException("Type does not support deletion");
        } else
        {

            S instance = findByUrn(getEntityClass(), object.getUrn());

            if (null != instance)
            {
                currentSession().delete(instance);
            } else
            {
                LOG.warn("Unable to locate object of type {} with unique ID {}", getEntityClass().getName(),
                        object.getUrn());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public S update(final S object)
    {
        T instance = null;

        try
        {
            instance = getEntityClass().newInstance();
            instance.copy(object);

            instance = (T) currentSession().merge(instance);

        } catch (InstantiationException e)
        {
            LOG.warn("Unable to instantiate object of type {}", getEntityClass().getName());
            LOG.debug(e.getMessage(), e);
        } catch (IllegalAccessException e)
        {
            LOG.debug(e.getMessage(), e);
        }
        return (S) instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(final Class<?> clazz, final String urn, final IAccount account)
    {
        Preconditions.checkNotNull(account, "Parameter 'account' must not be null");
        S object = null;

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        try
        {
            Query query = currentSession()
                    .createQuery("select e from " + entityName +
                            " e where e.account.systemUuid = :accountSystemUuid and e.systemUuid = :systemUuid")
                    .setParameter("accountSystemUuid", account.getSystemUuid())
                    .setParameter("systemUuid", UuidUtil.getUuidFromUrn(urn));

            object = (S) query.uniqueResult();
        } catch (IllegalArgumentException iae)
        {
            // no need to print stack trace; bad urn will be reported further up the chain
            // iae.printStackTrace();
            return null;
        }
        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(final Class<?> clazz, final String urn)
    {
        S object = null;
        try
        {
            if (urn == null)
            {
                return null;
            }

            object = null;

            String entityName = clazz.getName();

            /*
             * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8,
             * which restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class
             * identifier.
             *
             * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
             */
            Query query = currentSession().createQuery("select e from " + entityName + " e " +
                    "where e.systemUuid = :systemUuid")
                    .setParameter("systemUuid", UuidUtil.getUuidFromUrn(urn));

            object = (S) query.uniqueResult();
        } catch (IllegalArgumentException iae)
        {
            // no need to print stack trace; bad urn will be reported further up the chain
            // iae.printStackTrace();
            return null;
        }

        return object;
    }

    @SuppressWarnings("unchecked")
    public Collection<S> findByUuids(final Collection<UUID> uuids, final IAccount account)
    {
        final Collection<S> list = new ArrayList<>();

        if (uuids == null || uuids.size() == 0)
        {
            return list;
        }

        // TODO Add in Account restriction.
        for (Object o : criteria().add(Restrictions.in("systemUuid", uuids)).list())
        {
            list.add((S) o);
        }

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<S> findByAccount(final Class<?> clazz, final IAccount account)
    {
        Collection<S> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession().createQuery("select m from " + entityName + " m " +
                "where m.account.systemUuid = :accountSystemUuid")
                .setParameter("accountSystemUuid", account.getSystemUuid());

        for (Object o : listQuery.list())
        {
            list.add((S) o);
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<S> searchByMoniker(final Class<?> clazz, final String monikerEquals, final IAccount account)
    {
        Collection<S> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession()
                .createQuery("select m from " + entityName + " m " +
                        "where m.account.systemUuid = :accountSystemUuid " +
                        "and m.moniker = :moniker")
                .setParameter("accountSystemUuid", account.getSystemUuid())
                .setParameter("moniker", monikerEquals);

        for (Object o : listQuery.list())
        {
            list.add((S) o);
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<S> searchByMonikerLike(final Class<?> clazz, final String monikerLike, final IAccount account)
    {
        Collection<S> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession()
                .createQuery(
                        "select m from " + entityName + " m " +
                                "where m.account.systemUuid = :accountSystemUuid " +
                                "and m.moniker like :moniker")
                .setParameter("accountSystemUuid", account.getSystemUuid())
                .setParameter("moniker", monikerLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((S) o);
        }

        return list;
    }

    protected EntityPath<T> getPath()
    {
        throw new UnsupportedOperationException(getClass() + " does not support advanced queries yet.");
    }

    @Override
    public Collection<S> advancedQuery(final Predicate... predicates)
    {
        Collection<S> list = new ArrayList<>();

        HibernateQuery<T> query = new HibernateQuery<>(currentSession());

        for (T o : query.from(getPath()).where(predicates).fetch())
        {
            list.add((S) o);
        }

        return list;
    }

}
