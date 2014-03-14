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

package com.snapbundle.client;

import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

public interface IBaseClient<T>
{
    /**
     * Locate the object that has a case-sensitive match on the given URN.
     * <p/>
     * Returns a {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param urn
     * @return T if exists, or null if no match found
     * @throws ServiceException
     */
    T findByUrn(String urn) throws ServiceException;

    /**
     * Locate the object that has a case-sensitive match on the given URN.
     *
     * @param urn
     * @param viewType Field verbosity
     * @return T if exists, or null if no match found
     * @throws ServiceException
     */
    T findByUrn(String urn, ViewType viewType) throws ServiceException;

    /**
     * Creates a new instance of T, submitting every member field. Use this method if you aren't sure what fields
     * have been defined or if you aren't concerned about network utilization.
     *
     * @param instance
     * @return
     * @throws ServiceException
     */
    ResponseEntity create(T instance) throws ServiceException;

    /**
     * Creates a new instance of T, submitting only those fields included in the
     * JSON definition. Use this method if you are managing network bandwidth.
     *
     * @param instance
     * @return
     * @throws ServiceException
     */
    ResponseEntity create(JSONObject instance) throws ServiceException;
}
