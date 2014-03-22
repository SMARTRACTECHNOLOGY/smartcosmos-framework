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

package com.snapbundle.client.interaction;

import com.snapbundle.client.api.ICreateableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.context.IObjectInteraction;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

public interface IInteractionClient extends ICreateableBaseClient<IObjectInteraction>
{
    Collection<IObjectInteraction> listAll() throws ServiceException;

    Collection<IObjectInteraction> listAll(ViewType viewType) throws ServiceException;

    Collection<IObjectInteraction> findByDataLike(String dataLike) throws ServiceException;

    Collection<IObjectInteraction> findByDataLike(String dataLike, ViewType viewType) throws ServiceException;

    Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike) throws ServiceException;

    Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike, ViewType viewType) throws ServiceException;
}
