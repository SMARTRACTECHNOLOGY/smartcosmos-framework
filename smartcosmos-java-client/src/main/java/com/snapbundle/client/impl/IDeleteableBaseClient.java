/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.client.impl;

import com.snapbundle.client.connectivity.ServiceException;
import org.json.JSONObject;

/**
 * Type-safe deletion routines for SnapBundle entities that support permanent removal. Deletion is predicated strictly
 * upon providing a valid system-assigned URN; if either a type-safe instance or a JSONObject is provided, only the
 * {@link com.snapbundle.model.base.IUrnNamespace#getUrn()} method or {@link com.snapbundle.Field#URN_FIELD} field is
 * utilized by the deletion routines.
 * <p/>
 * Not every creatable entity supports deletion by design. Some entities, like
 * {@link com.snapbundle.model.context.IObject} are explicitly preserved for historical reporting purposes and should be
 * set inactive with the {@link com.snapbundle.model.base.INamedObject#setActive(boolean)} method.
 *
 * @param <T> One of the deletable SnapBundle contextual entities.
 * @see com.snapbundle.model.context.IFile
 * @see com.snapbundle.model.extension.IExtension
 * @see com.snapbundle.model.context.IMetadata
 * @see com.snapbundle.model.context.IRelationship
 * @see com.snapbundle.model.context.ITag
 * @see com.snapbundle.model.context.IObjectAddress
 */
public interface IDeleteableBaseClient<T>
{
    /**
     * Removes an existing instance, if it exists.
     *
     * @param instance
     * @throws com.snapbundle.client.connectivity.ServiceException
     */
    void delete(T instance) throws ServiceException;

    /**
     * Removes an existing instance based on the {@link com.snapbundle.Field#URN_FIELD} value, if it exists.
     *
     * @param instance
     * @throws ServiceException
     */
    void delete(JSONObject instance) throws ServiceException;
}
