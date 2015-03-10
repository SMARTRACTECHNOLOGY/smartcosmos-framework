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

import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.INamedObjectSearchDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collection;

public class AbstractNamedObjectDAOImpl<U extends INamedObject, V extends U>
        extends AbstractDAOImpl<U, V> implements INamedObjectSearchDAO<U>
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

        Query listQuery = currentSession().createQuery("select m from " + entityName +
                " m where m.account.uniqueId = :uniqueId and m.name like :name")
                .setParameter("uniqueId", account.getUniqueId())
                .setParameter("name", nameLike + "%");

        for (Object o : listQuery.list())
        {
            list.add((U) o);
        }

        return list;
    }
}
