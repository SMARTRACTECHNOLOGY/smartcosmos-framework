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

package net.smartcosmos.client.object;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.client.impl.endpoint.ObjectEndpoints;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link net.smartcosmos.objects.model.context.IObject} instances.
 */
public interface IObjectClient extends IUpdateableBaseClient<IObject>
{
    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} using a case-sensitive
     * exact match.
     * <p/>
     * Returns a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @return Matching object
     */
    IObject findByExactObjectUrn(String objectUrn) throws ServiceException;

    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} using a case-sensitive
     * exact match.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param viewType  Field verbosity
     * @return Matching object
     */
    IObject findByExactObjectUrn(String objectUrn, ViewType viewType) throws ServiceException;

    /**
     * Complex query for a collection of matching objects.
     *
     * @param builder Builder that defines the query to perform
     * @return Non-null collection of matching entities; collection may have a size of 0 to indicate no matches found
     */
    Collection<IObject> query(ObjectEndpoints.Builder builder) throws ServiceException;
}
