package net.smartcosmos.platform.api.dao;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.querydsl.core.types.Predicate;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.domain.IPage;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

/**
 * Initially created by tcross on November 16, 2015.
 */
public interface IAbstractDAO<S extends IDomainResource<S>, T extends S> extends IAdvancedDAO<S>

{
    @Override
     Collection<S> advancedQuery(final Predicate... predicates);

    @Override
     boolean canDelete();

    /*
     * (non-Javadoc)
     *
     * @see net.smartcosmos.platform.dao.IPageProvider#count()
     */
    @Override
     Long count();

    @Override
     void delete(final S object);

    @Override
     void deleteJson(final String object) throws JsonParseException, JsonMappingException, IOException;

    @Override
     Collection<S> findByAccount(final Class<?> clazz, final IAccount account);

    @Override
     Collection<String> findByAccountJson(final IAccount account) throws JsonProcessingException;

    @Override
     S findByUrn(final Class<?> clazz, final String urn);

    @Override
     S findByUrn(final Class<?> clazz, final String urn, final IAccount account);

    @Override
     String findByUrnJson(final String urn) throws JsonProcessingException;

    @Override
     String findByUrnJson(final String urn, final IAccount account) throws JsonProcessingException;

     Collection<S> findByUuids(final Collection<UUID> uuids, final IAccount account);

    @Override
     Collection<String> findByUuidsJson(final Collection<UUID> uuids, final IAccount account) throws JsonProcessingException;

    /**
     * Returns the entity class managed by this DAO.
     *
     * @return the entity class managed by this DAO
     */
     Class<T> getEntityClass();

    @Override
     S insert(final S object);

    @Override
     String insertJson(final String object) throws IOException;

    /*
     * (non-Javadoc)
     *
     * @see net.smartcosmos.platform.dao.IPageProvider#page(int, int)
     */
    @Override
     IPage<S> page(final int page, final int pageSize);

    @Override
     Collection<S> searchByMoniker(final Class<?> clazz, final String monikerEquals, final IAccount account);

    @Override
     Collection<S> searchByMonikerLike(final Class<?> clazz, final String monikerLike, final IAccount account);

    @Override
     S update(final S object);

    @Override
     String updateJson(final String object) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;

    @Override
     S upsert(final S object);

    @Override
     String upsertJson(final String object) throws IOException;
}
