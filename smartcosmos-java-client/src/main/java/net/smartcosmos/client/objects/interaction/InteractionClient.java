package net.smartcosmos.client.objects.interaction;

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
import net.smartcosmos.client.impl.base.AbstractCreateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.InteractionEndpoints;
import net.smartcosmos.objects.model.context.IObjectInteraction;
import net.smartcosmos.objects.pojo.context.ObjectInteraction;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class InteractionClient extends AbstractCreateableBaseClient<IObjectInteraction> implements IInteractionClient
{
    InteractionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IObjectInteraction findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, InteractionEndpoints.findByUrn(urn, viewType), ObjectInteraction.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, InteractionEndpoints.create());
    }

    @Override
    public Collection<IObjectInteraction> listAll(ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context, getClient());
        return command.call(ObjectInteraction.class, InteractionEndpoints.listAllInteractions(viewType));
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrn(String objectUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context, getClient());
        return command.call(ObjectInteraction.class, InteractionEndpoints.findByObjectUrn(objectUrn, viewType));
    }

    @Override
    public Collection<IObjectInteraction> listAll() throws ServiceException
    {
        return listAll(ViewType.Standard);
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrn(String objectUrn) throws ServiceException
    {
        return findByObjectUrn(objectUrn, ViewType.Standard);
    }
}
