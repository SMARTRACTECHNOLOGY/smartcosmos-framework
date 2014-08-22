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
package net.smartcosmos.client.objects.extension;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.ExtensionEndpoints;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.extension.Extension;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static net.smartcosmos.Field.URN_FIELD;

class ExtensionClient extends AbstractUpdateableBaseClient<IExtension> implements IExtensionClient
{
    ExtensionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IExtension findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ExtensionEndpoints.findByUrn(urn, viewType), Extension.class);
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
            DeleteCommand command = new DeleteCommand(context);
            command.call(Object.class, ExtensionEndpoints.delete(instance.getString(URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(IExtension instance) throws ServiceException
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
    public Collection<IExtension> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IExtension> command = new GetCollectionCommand<>(context);
        return command.call(Extension.class, ExtensionEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public Collection<IExtension> catalog() throws ServiceException
    {
        GetCollectionCommand<IExtension> command = new GetCollectionCommand<>(context);
        return command.call(Extension.class, ExtensionEndpoints.catalog());
    }

    @Override
    public IExtension getPublishedExtension(String urn) throws ServiceException
    {
        GetCommand<IExtension> command = new GetCommand<>(context);
        return command.call(Extension.class, ExtensionEndpoints.publishedExtension(urn));
    }

    @Override
    public Collection<IExtension> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
