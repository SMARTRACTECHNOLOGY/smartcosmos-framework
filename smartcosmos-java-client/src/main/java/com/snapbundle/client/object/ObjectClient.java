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

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.ObjectEndpoints;
import com.snapbundle.client.impl.AbstractUpdateableBaseClient;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.client.impl.command.GetCommand;
import com.snapbundle.model.context.IObject;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.ObjectImpl;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

class ObjectClient extends AbstractUpdateableBaseClient<IObject> implements IObjectClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(ObjectClient.class);

    ObjectClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IObject findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ObjectEndpoints.findByUrn(urn, viewType), ObjectImpl.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, ObjectEndpoints.create());
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, ObjectEndpoints.update());
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn) throws ServiceException
    {
        return findByExactObjectUrn(objectUrn, ViewType.Standard);
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn, ViewType viewType) throws ServiceException
    {
        GetCommand<IObject> command = new GetCommand<>(context);
        return command.call(ObjectImpl.class, ObjectEndpoints.findByExactObjectUrn(objectUrn, viewType));
    }

    @Override
    public Collection<IObject> query(ObjectEndpoints.Builder builder) throws ServiceException
    {
        GetCollectionCommand<IObject> command = new GetCollectionCommand<>(context);
        return command.call(ObjectImpl.class, builder.build());
    }
}
