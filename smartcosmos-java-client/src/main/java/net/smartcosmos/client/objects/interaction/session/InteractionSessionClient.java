/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.client.objects.interaction.session;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.endpoint.ObjectInteractionSessionEndpoints;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.objects.pojo.context.ObjectInteractionSession;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class InteractionSessionClient extends AbstractUpdateableBaseClient<IObjectInteractionSession> implements IInteractionSessionClient
{
    InteractionSessionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public Collection<IObjectInteractionSession> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteractionSession> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteractionSession.class, ObjectInteractionSessionEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, ObjectInteractionSessionEndpoints.update());
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, ObjectInteractionSessionEndpoints.create());
    }

    @Override
    public IObjectInteractionSession findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ObjectInteractionSessionEndpoints.findByUrn(urn, viewType), ObjectInteractionSession.class);
    }

    @Override
    public Collection<IObjectInteractionSession> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
