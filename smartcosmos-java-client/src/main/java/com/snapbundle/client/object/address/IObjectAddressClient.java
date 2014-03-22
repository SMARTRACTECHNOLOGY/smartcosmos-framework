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

package com.snapbundle.client.object.address;

import com.snapbundle.client.api.IDeleteableBaseClient;
import com.snapbundle.client.api.IUpdateableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.context.IObjectAddress;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

public interface IObjectAddressClient extends IUpdateableBaseClient<IObjectAddress>, IDeleteableBaseClient<IObjectAddress>
{
    IObjectAddress findByUrn(String objectUrn, String urn) throws ServiceException;

    IObjectAddress findByUrn(String objectUrn, String urn, ViewType viewType) throws ServiceException;

    Collection<IObjectAddress> findLastN(String objectUrn, int count) throws ServiceException;

    Collection<IObjectAddress> findLastN(String objectUrn, int count, ViewType viewType) throws ServiceException;
}
