/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.client.impl;

import net.smartcosmos.client.connectivity.ServiceException;
import org.json.JSONObject;

/**
 * Type-safe deletion routines for SMART COSMOS entities that support permanent removal. Deletion is predicated strictly
 * upon providing a valid system-assigned URN; if either a type-safe instance or a JSONObject is provided, only the
 * {@link net.smartcosmos.model.base.IUrnNamespace#getUrn()} method or {@link net.smartcosmos.Field#URN_FIELD} field is
 * utilized by the deletion routines.
 * <p/>
 * Not every creatable entity supports deletion by design. Some entities, like
 * {@link net.smartcosmos.objects.model.context.IObject} are explicitly preserved for historical reporting purposes and should be
 * set inactive with the {@link net.smartcosmos.model.base.INamedObject#setActive(boolean)} method.
 *
 * @param <T> One of the deletable SMART COSMOS contextual entities.
 * @see net.smartcosmos.objects.model.context.IFile
 * @see net.smartcosmos.platform.api.extension.IExtension
 * @see net.smartcosmos.model.context.IMetadata
 * @see net.smartcosmos.objects.model.context.IRelationship
 * @see net.smartcosmos.objects.model.context.ITag
 * @see net.smartcosmos.objects.model.context.IObjectAddress
 */
public interface IDeleteableBaseClient<T>
{
    /**
     * Removes an existing instance, if it exists.
     *
     * @param instance
     * @throws net.smartcosmos.client.connectivity.ServiceException
     */
    void delete(T instance) throws ServiceException;

    /**
     * Removes an existing instance based on the {@link net.smartcosmos.Field#URN_FIELD} value, if it exists.
     *
     * @param instance
     * @throws ServiceException
     */
    void delete(JSONObject instance) throws ServiceException;
}
