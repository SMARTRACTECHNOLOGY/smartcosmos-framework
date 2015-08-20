package net.smartcosmos.client.impl;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.pojo.base.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Type-safe upsert routines for SMART COSMOS entities either by upserting an explicit strongly typed instance or
 * through a more fluid JSON definition. Upsert is a hybrid operation that checks to see if a specific key already
 * exists in order to choose between either an <b>up</b>date or an in<b>sert</b> operation.
 * <p/>
 * The {@link net.smartcosmos.model.context.IMetadata} and {@link net.smartcosmos.objects.model.context.ITag} entities
 * rely exclusively on the {@link net.smartcosmos.model.base.INamedObject#getName()} field for determining existence.
 * The {@link net.smartcosmos.objects.model.context.IRelationship} relies on the relationship being defined by the
 * {@link net.smartcosmos.objects.model.context.IRelationship#getEntityReferenceType()},
 * {@link net.smartcosmos.objects.model.context.IRelationship#getReferenceUrn()},
 * {@link net.smartcosmos.objects.model.context.IRelationship#getRelatedEntityReferenceType()}, and
 * {@link net.smartcosmos.objects.model.context.IRelationship#getRelatedReferenceUrn()}.
 * <p/>
 * Every upserted entity that results in an insert is still assigned a system-generated URN. Most clients provide
 * additional search methods beyond the standard {@link IFindableBaseClient#findByUrn(String)}
 * method, so generally it isn't necessary to explicitly remember the system-generated URN, which is especially true
 * for upsertable entities.
 *
 * @param <T> One of the upsertable SMART COSMOS contextual entities.
 * @see net.smartcosmos.model.context.IMetadata
 * @see net.smartcosmos.objects.model.context.IRelationship
 * @see net.smartcosmos.objects.model.context.ITag
 */
public interface IUpsertableBaseClient<T> extends IFindableBaseClient<T>
{
    /**
     * Submits every field for update if a record with a matching system assigned URN already exists, or inserts a new
     * record if no matching system URN already exists. Use this method if you aren't sure what fields changed or if
     * you aren't concerned about network utilization.
     *
     * @param instance entity instance
     */
    ResponseEntity upsert(T instance) throws ServiceException;

    ResponseEntity upsert(JSONObject instance) throws ServiceException;

    Collection<ResponseEntity> upsert(JSONArray jsonArray) throws ServiceException;

}
