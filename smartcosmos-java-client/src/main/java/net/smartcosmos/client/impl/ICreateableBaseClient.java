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
import net.smartcosmos.pojo.base.ResponseEntity;
import org.json.JSONObject;

/**
 * Type-safe creation routines for SMART COSMOS entities either by providing an explicit strongly typed instance or
 * through a more fluid JSON definition.
 * <p/>
 * Every creatable entity will be assigned a system-generated URN. Most clients provide additional search methods
 * beyond the standard {@link IFindableBaseClient#findByUrn(String)} method, so generally it
 * isn't necessary to explicitly remember the system-generated URN.
 *
 * @param <T> One of the SMART COSMOS contextual entities.
 * @see net.smartcosmos.objects.model.extension.IExtension
 * @see net.smartcosmos.objects.model.context.IFile
 * @see net.smartcosmos.objects.model.context.IDevice
 * @see net.smartcosmos.objects.model.geo.IGeospatialEntry
 * @see net.smartcosmos.model.context.IMetadata
 * @see net.smartcosmos.objects.model.context.IRelationship
 * @see net.smartcosmos.objects.model.context.ITag
 * @see net.smartcosmos.objects.model.context.ITimelineEntry
 * @see net.smartcosmos.objects.model.context.IObject
 * @see net.smartcosmos.objects.model.context.IObjectAddress
 * @see net.smartcosmos.objects.model.context.IObjectInteraction
 * @see net.smartcosmos.objects.model.context.IObjectInteractionSession
 * @see net.smartcosmos.model.context.IUser
 */
public interface ICreateableBaseClient<T> extends IFindableBaseClient<T>
{
    /**
     * Creates a new instance of T, submitting every member field. Use this method if you aren't sure what fields
     * have been defined or if you aren't concerned about network utilization.
     *
     * @param instance
     * @return response entity that contains the system-assigned URN in
     * {@link net.smartcosmos.pojo.base.ResponseEntity#getMessage()} when the
     * {@link net.smartcosmos.pojo.base.ResponseEntity#getCode()} equals {@link net.smartcosmos.pojo.base.Result#OK}
     * @throws net.smartcosmos.client.connectivity.ServiceException
     */
    ResponseEntity create(T instance) throws ServiceException;

    /**
     * Creates a new instance of T, submitting only those fields included in the
     * JSON definition. Use this method if you are managing network bandwidth.
     *
     * @param instance JSON object with the minimum set of fields required for the specific entity T
     * @return response entity that contains the system-assigned URN in
     * {@link net.smartcosmos.pojo.base.ResponseEntity#getMessage()} when the
     * {@link net.smartcosmos.pojo.base.ResponseEntity#getCode()} equals {@link net.smartcosmos.pojo.base.Result#OK}
     * @throws ServiceException
     */
    ResponseEntity create(JSONObject instance) throws ServiceException;
}
