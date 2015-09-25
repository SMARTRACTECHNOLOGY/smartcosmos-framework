package net.smartcosmos.client.objects.extension;

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

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.ExtensionEndpoints;
import net.smartcosmos.model.extension.IExternalExtension;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.extension.ExternalExtension;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static net.smartcosmos.Field.URN_FIELD;

class ExtensionClient extends AbstractUpdateableBaseClient<IExternalExtension> implements IExtensionClient
{
    ExtensionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IExternalExtension findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ExtensionEndpoints.findByUrn(urn, viewType), ExternalExtension.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, ExtensionEndpoints.create());
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, ExtensionEndpoints.update());
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context, getClient());
            command.call(Object.class, ExtensionEndpoints.delete(instance.getString(URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(IExternalExtension instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            delete(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<IExternalExtension> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IExternalExtension> command = new GetCollectionCommand<>(context, getClient());
        return command.call(ExternalExtension.class, ExtensionEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public Collection<IExternalExtension> catalog() throws ServiceException
    {
        GetCollectionCommand<IExternalExtension> command = new GetCollectionCommand<>(context, getClient());
        return command.call(ExternalExtension.class, ExtensionEndpoints.catalog());
    }

    @Override
    public IExternalExtension getPublishedExtension(String urn) throws ServiceException
    {
        GetCommand<IExternalExtension> command = new GetCommand<>(context, getClient());
        return command.call(ExternalExtension.class, ExtensionEndpoints.publishedExtension(urn));
    }

    @Override
    public Collection<IExternalExtension> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
