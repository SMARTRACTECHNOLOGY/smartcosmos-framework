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

package com.snapbundle.client.api;

import org.json.JSONObject;

public interface IUpsertableBaseClient<T> extends IFindableBaseClient<T>
{
    /**
     * Submits every field for update if a record with a matching system assigned URN already exists, or inserts a new
     * record if no matching system URN already exists. Use this method if you aren't sure what fields changed or if
     * you aren't concerned about network utilization.
     *
     * @param instance
     */
    void upsert(T instance) throws ServiceException;

    void upsert(JSONObject instance) throws ServiceException;
}
