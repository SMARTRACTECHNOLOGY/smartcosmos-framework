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
import org.json.JSONObject;

/**
 * Type-safe update routines for SMART COSMOS entities either by providing an explicit strongly typed instance or
 * through a more fluid JSON definition. An update operation is predicated strictly upon providing a valid
 * system-assigned URN; if either a type-safe instance or a JSONObject is provided, only the
 * {@link net.smartcosmos.model.base.IUrnNamespace#getUrn()} method or {@link net.smartcosmos.Field#URN_FIELD} field is
 * utilized by the update routines to find the original persisted object. Once that object is found, the new values
 * are merged with the existing values.
 * <p/>
 * <b>NOTE:</b> For details on how to reset an entity's moniker field back to a <code>NULL</code> value after it has
 * been assigned a non-null value, please refer to the class {@link net.smartcosmos.model.base.IMoniker} JavaDocs.
 *
 * @param <T> One of the updateable SMART COSMOS contextual entities.
 * @see net.smartcosmos.model.extension.IExtension
 * @see net.smartcosmos.objects.model.context.IDevice
 * @see net.smartcosmos.model.geo.IGeospatialEntry
 * @see net.smartcosmos.objects.model.context.IObject
 * @see net.smartcosmos.objects.model.context.IObjectAddress
 * @see net.smartcosmos.objects.model.context.IObjectInteractionSession
 * @see net.smartcosmos.objects.model.context.ITimelineEntry
 * @see net.smartcosmos.model.context.IUser
 */
public interface IUpdateableBaseClient<T> extends ICreateableBaseClient<T>
{
    /**
     * Submits every field for update. Use this method if you aren't sure what fields changed or if
     * you aren't concerned about network utilization.
     *
     * @param instance
     */
    void update(T instance) throws ServiceException;

    void update(JSONObject instance) throws ServiceException;
}
