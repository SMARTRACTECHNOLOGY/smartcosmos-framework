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

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.INamedObjectSearchDAO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class AbstractNamedObjectDAOImpl<U extends INamedObject, V extends U> extends AbstractDAOImpl<U, V> implements
        INamedObjectSearchDAO<U>
{
    protected AbstractNamedObjectDAOImpl(Class<V> classInstance, SessionFactory sessionFactory)
    {
        super(classInstance, sessionFactory);
    }

    protected AbstractNamedObjectDAOImpl(Class<V> classInstance, SessionFactory sessionFactory, boolean canDelete)
    {
        super(classInstance, sessionFactory, canDelete);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<U> findByNameLike(Class<?> clazz, String nameLike, IAccount account)
    {
        Collection<U> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession()
                .createQuery("select m from " + entityName + " m where m.account.urn = :urn and m.name like :name")
                .setParameter("urn", UUID.fromString(account.getUrn())).setParameter("name", nameLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((U) o);
        }

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<U> findByNameExact(Class<?> clazz, String name, IAccount account)
    {
        Collection<U> list = new ArrayList<>();

        String entityName = clazz.getName();

        /*
         * NOTE: The risk of SQL injection here is virtually zero because of the Java Language Specification 3.8, which
         * restricts special characters like semicolon (;), dash (-), parentheses, etc. as part of a class identifier.
         * 
         * See http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
         */
        Query listQuery = currentSession().createQuery("select m from " + entityName + " m where m.name = :name")
                .setParameter("name", name);

        for (Object o : listQuery.list())
        {
            list.add((U) o);
        }

        return list;
    }
}
