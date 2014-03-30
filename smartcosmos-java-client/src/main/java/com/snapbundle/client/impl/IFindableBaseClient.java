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
import com.snapbundle.util.json.ViewType;

/**
 * Locates a single, specific SnapBundle entity by its system-assigned URN.
 *
 * @param <T> One of the SnapBundle contextual entities.
 * @see com.snapbundle.model.extension.IExtension
 * @see com.snapbundle.model.context.IDevice
 * @see com.snapbundle.model.context.IFile
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
public interface IFindableBaseClient<T>
{
    /**
     * Locate the contextual entity that has a case-sensitive match on the given system assigned URN.
     * <p/>
     * Returns a {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param urn System generated URN assigned to the entity at creation time
     * @return Instance of T with all {@link com.snapbundle.util.json.ViewType#Standard} fields populated
     * @throws com.snapbundle.client.connectivity.ServiceException if no matching URN exists
     */
    T findByUrn(String urn) throws ServiceException;

    /**
     * Locate the object that has a case-sensitive match on the given URN.
     *
     * @param urn      System generated URN assigned to the entity at creation time
     * @param viewType Field verbosity
     * @return Instance of T with appropriate fields populated based on the specified viewType
     * @throws ServiceException if no matching URN exists
     */
    T findByUrn(String urn, ViewType viewType) throws ServiceException;
}
