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
import com.snapbundle.pojo.base.ResponseEntity;
import org.json.JSONObject;

/**
 * Type-safe creation routines for SnapBundle entities either by providing an explicit strongly typed instance or
 * through a more fluid JSON definition.
 * <p/>
 * Every creatable entity will be assigned a system-generated URN. Most clients provide additional search methods
 * beyond the standard {@link IFindableBaseClient#findByUrn(String)} method, so generally it
 * isn't necessary to explicitly remember the system-generated URN.
 *
 * @param <T> One of the SnapBundle contextual entities.
 * @see com.snapbundle.model.extension.IExtension
 * @see com.snapbundle.model.context.IFile
 * @see com.snapbundle.model.context.IDevice
 * @see com.snapbundle.model.geo.IGeospatialEntry
 * @see com.snapbundle.model.context.IMetadata
 * @see com.snapbundle.model.context.IRelationship
 * @see com.snapbundle.model.context.ITag
 * @see com.snapbundle.model.context.ITimelineEntry
 * @see com.snapbundle.model.context.IObject
 * @see com.snapbundle.model.context.IObjectAddress
 * @see com.snapbundle.model.context.IObjectInteraction
 * @see com.snapbundle.model.context.IObjectInteractionSession
 * @see com.snapbundle.model.context.IUser
 */
public interface ICreateableBaseClient<T> extends IFindableBaseClient<T>
{
    /**
     * Creates a new instance of T, submitting every member field. Use this method if you aren't sure what fields
     * have been defined or if you aren't concerned about network utilization.
     *
     * @param instance
     * @return response entity that contains the system-assigned URN in
     * {@link com.snapbundle.pojo.base.ResponseEntity#getMessage()} when the
     * {@link com.snapbundle.pojo.base.ResponseEntity#getCode()} equals {@link com.snapbundle.pojo.base.Result#OK}
     * @throws com.snapbundle.client.connectivity.ServiceException
     */
    ResponseEntity create(T instance) throws ServiceException;

    /**
     * Creates a new instance of T, submitting only those fields included in the
     * JSON definition. Use this method if you are managing network bandwidth.
     *
     * @param instance JSON object with the minimum set of fields required for the specific entity T
     * @return response entity that contains the system-assigned URN in
     * {@link com.snapbundle.pojo.base.ResponseEntity#getMessage()} when the
     * {@link com.snapbundle.pojo.base.ResponseEntity#getCode()} equals {@link com.snapbundle.pojo.base.Result#OK}
     * @throws ServiceException
     */
    ResponseEntity create(JSONObject instance) throws ServiceException;
}
