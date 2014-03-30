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

package com.snapbundle.client.impl.command;

import com.snapbundle.client.connectivity.ServiceException;
import com.snapbundle.pojo.base.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

public interface ICommand<T, E>
{
    T call(Class<? extends E> clazz, String path) throws ServiceException;

    T call(Class<? extends E> clazz, String path, JSONObject inputJson) throws ServiceException;

    Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException;
}
