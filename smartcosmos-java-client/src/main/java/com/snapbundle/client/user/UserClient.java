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

package com.snapbundle.client.user;

import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.client.impl.AbstractClient;
import com.snapbundle.model.context.IUser;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

class UserClient extends AbstractClient implements IUserClient
{
    private final ServerContext context;

    UserClient(ServerContext context)
    {
        this.context = context;
    }

    @Override
    public void update(IUser instance) throws ServiceException
    {

    }

    @Override
    public IUser findByUrn(String urn) throws ServiceException
    {
        return null;
    }

    @Override
    public IUser findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public ResponseEntity create(IUser instance) throws ServiceException
    {
        return null;
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return null;
    }
}
