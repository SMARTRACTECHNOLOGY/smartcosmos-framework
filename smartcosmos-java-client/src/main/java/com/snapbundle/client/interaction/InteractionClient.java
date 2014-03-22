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

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.InteractionEndpoints;
import com.snapbundle.client.impl.AbstractCreateableBaseClient;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.model.context.IObjectInteraction;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.ObjectInteraction;
import com.snapbundle.util.json.ViewType;
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
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteraction.class, InteractionEndpoints.listAllInteractions(viewType));
    }

    @Override
    public Collection<IObjectInteraction> findByDataLike(String dataLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteraction.class, InteractionEndpoints.findByDataLike(dataLike, viewType));
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteraction.class, InteractionEndpoints.findByObjectUrnLike(objectUrnLike, viewType));
    }

    @Override
    public Collection<IObjectInteraction> listAll() throws ServiceException
    {
        return listAll(ViewType.Standard);
    }

    @Override
    public Collection<IObjectInteraction> findByDataLike(String dataLike) throws ServiceException
    {
        return findByDataLike(dataLike, ViewType.Standard);
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike) throws ServiceException
    {
        return findByObjectUrnLike(objectUrnLike, ViewType.Standard);
    }
}
