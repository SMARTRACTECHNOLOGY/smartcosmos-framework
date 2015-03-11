package net.smartcosmos.platform.dao;

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

import com.google.common.base.Preconditions;
import io.dropwizard.hibernate.AbstractDAO;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.ISearchAssembly;
import net.smartcosmos.platform.api.dao.IBaseDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractDAOImpl<S extends IDomainResource, T extends S>
        extends AbstractDAO<T> implements IBaseDAO<S>
{
    private static Logger logger = LoggerFactory.getLogger(AbstractDAOImpl.class);

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
        T instance = null;

        if (null == object)
        {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        S findResult = findById(classInstance, object.getUniqueId());

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
            logger.warn("Unable to instantiate object of type " + classInstance.getName());
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
            logger.warn("Attempt to delete an immutable object detected");
            throw new UnsupportedOperationException("Type does not support deletion");
        } else
        {

            S instance = findById(classInstance, object.getUniqueId());

            if (null != instance)
            {
                currentSession().delete(instance);
            } else
            {
                logger.warn("Unable to locate object of type " + classInstance.getName() +
                        " with unique ID " + object.getUniqueId());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(S object)
    {
        T instance = null;

        try
        {
            instance = classInstance.newInstance();
            instance.copy(object);

            currentSession().merge(instance);

        } catch (InstantiationException e)
        {
            logger.warn("Unable to instantiate object of type " + classInstance.getName());
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    protected EntityReferenceType getEntityReferenceType()
    {
        throw new UnsupportedOperationException("Base class doesn't provide a default entity reference type");
    }

    public String generateHql(Class<?> clazz, ISearchAssembly assembly)
    {
        String entityName = clazz.getName();
        String hql = "select m from " + entityName + " m ";

        String joinClause = "";
        if (assembly.hasJoin())
        {
            switch (assembly.getJoin())
            {
                case Tag:
                    hql += ", tag t, tag_assignment ta ";
                    joinClause = " and ta.entityReferenceType = '" + getEntityReferenceType() +
                            "' and ta.referenceUrn = m.urn and t.urn = ta.tag.urn ";
                    break;
                case File:
                    hql += ", file f ";
                    joinClause = " and f.entityReferenceType = '" + getEntityReferenceType() +
                            "' and f.referenceUrn = m.urn ";
                    break;
                case Metadata:
                    hql += ", metadata meta ";
                    joinClause = " and meta.entityReferenceType = '" + getEntityReferenceType() +
                            "' and meta.referenceUrn = m.urn ";
                    break;
                default:
                    throw new IllegalStateException("Unrecognized join clause: " + assembly.getJoin().toString());
            }
        }

        String parametricWhereClause = assembly.getWhereClause();
        hql += "where m.account.uniqueId = :uniqueId " +
                joinClause +
                ((parametricWhereClause != null)
                        ? " and " + parametricWhereClause
                        : "");

        return hql;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findById(Class<?> clazz, long id)
    {
        S object = null;

        String entityName = clazz.getName();

        Query query = currentSession()
                .createQuery("select e from " + entityName + " e where e.uniqueId = :uniqueId")
                .setParameter("uniqueId", id);

        object = (S) query.uniqueResult();

        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public S findByUrn(Class<?> clazz, String urn, IAccount account)
    {
        Preconditions.checkNotNull(account, "Parameter 'account' must not be null");
        S object = null;

        String entityName = clazz.getName();

        Query query = currentSession()
                .createQuery("select e from " + entityName +
                        " e where e.account.uniqueId = :accountId and e.urn = :urn")
                .setParameter("accountId", account.getUniqueId())
                .setParameter("urn", urn);

        object = (S) query.uniqueResult();

        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<S> findByAccount(Class<?> clazz, IAccount account)
    {
        Collection<S> list = new ArrayList<>();

        String entityName = clazz.getName();

        Query listQuery = currentSession().createQuery("select m from " + entityName + " m where m.account.urn = :urn")
                .setParameter("urn", account.getUrn());

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

        Query listQuery = currentSession().createQuery("select m from " + entityName +
                " m where m.account.uniqueId = :uniqueId and m.moniker = :moniker")
                .setParameter("uniqueId", account.getUniqueId())
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

        Query listQuery = currentSession().createQuery("select m from " + entityName +
                " m where m.account.uniqueId = :uniqueId and m.moniker like :moniker")
                .setParameter("uniqueId", account.getUniqueId())
                .setParameter("moniker", monikerLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((IDomainResource) o);
        }

        return list;
    }
}

