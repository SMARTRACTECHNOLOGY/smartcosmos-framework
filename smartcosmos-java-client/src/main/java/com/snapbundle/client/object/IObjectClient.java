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

package com.snapbundle.client.object;

import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.endpoint.ObjectEndpoints;
import com.snapbundle.client.impl.IUpdateableBaseClient;
import com.snapbundle.model.context.IObject;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

public interface IObjectClient extends IUpdateableBaseClient<IObject>
{
    /**
     * Find a specific {@link com.snapbundle.model.context.IObject#getObjectUrn()} using a case-sensitive
     * exact match.
     * <p/>
     * Returns a {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @return Matching object
     */
    IObject findByExactObjectUrn(String objectUrn) throws ServiceException;

    /**
     * Find a specific {@link com.snapbundle.model.context.IObject#getObjectUrn()} using a case-sensitive
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
