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
import net.smartcosmos.util.json.ViewType;

/**
 * Locates a single, specific SnapBundle entity by its system-assigned URN.
 *
 * @param <T> One of the SnapBundle contextual entities.
 * @see net.smartcosmos.objects.model.extension.IExtension
 * @see net.smartcosmos.objects.model.context.IDevice
 * @see net.smartcosmos.objects.model.context.IFile
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
public interface IFindableBaseClient<T>
{
    /**
     * Locate the contextual entity that has a case-sensitive match on the given system assigned URN.
     * <p/>
     * Returns a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param urn System generated URN assigned to the entity at creation time
     * @return Instance of T with all {@link net.smartcosmos.util.json.ViewType#Standard} fields populated
     * @throws net.smartcosmos.client.connectivity.ServiceException if no matching URN exists
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
