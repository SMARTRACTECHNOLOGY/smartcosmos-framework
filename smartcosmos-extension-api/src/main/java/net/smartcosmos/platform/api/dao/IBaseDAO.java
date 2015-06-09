package net.smartcosmos.platform.api.dao;

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

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;

import java.util.Collection;

/**
 * Minimal DAO implementation that supports basic CRUD, with an optional delete and two basic finder methods.
 *
 * @param <T>
 */
public interface IBaseDAO<T extends IDomainResource> extends IMonikerSearchDAO
{
    T findByUrn(Class<?> clazz, String urn);

    T findByUrn(Class<?> clazz, String urn, IAccount account);

    Collection<T> findByAccount(Class<?> clazz, IAccount account);

    T insert(T object);

    T upsert(T object);

    boolean canDelete();

    void delete(T object);

    void update(T object);
}
