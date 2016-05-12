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
import net.smartcosmos.util.json.ViewType;

/**
 * Locates a single, specific SMART COSMOS entity by its system-assigned URN.
 *
 * @param <T>
 *            One of the SMART COSMOS contextual entities.
 * @see net.smartcosmos.model.extension.IExternalExtension
 * @see net.smartcosmos.objects.model.context.IFile
 * @see net.smartcosmos.model.context.IMetadata
 * @see net.smartcosmos.objects.model.context.IRelationship
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
     * <p>
     * Returns a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param urn
     *            System generated URN assigned to the entity at creation time
     * @return Instance of T with all {@link net.smartcosmos.util.json.ViewType#Standard} fields populated
     * @throws ServiceException
     *             if no matching URN exists
     */
    T findByUrn(String urn) throws ServiceException;

    /**
     * Locate the object that has a case-sensitive match on the given URN.
     *
     * @param urn
     *            System generated URN assigned to the entity at creation time
     * @param viewType
     *            Field verbosity
     * @return Instance of T with appropriate fields populated based on the specified viewType
     * @throws ServiceException
     *             if no matching URN exists
     */
    T findByUrn(String urn, ViewType viewType) throws ServiceException;
}
