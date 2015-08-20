package net.smartcosmos.client.objects.object;

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

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.ObjectEndpoints;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class ObjectClient extends AbstractUpdateableBaseClient<IObject> implements IObjectClient
{
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
