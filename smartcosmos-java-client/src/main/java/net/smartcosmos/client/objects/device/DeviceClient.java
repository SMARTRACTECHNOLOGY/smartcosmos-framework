package net.smartcosmos.client.objects.device;

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
import net.smartcosmos.client.impl.endpoint.DeviceEndpoints;
import net.smartcosmos.objects.model.context.IDevice;
import net.smartcosmos.objects.pojo.context.Device;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class DeviceClient extends AbstractUpdateableBaseClient<IDevice> implements IDeviceClient
{
    DeviceClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, DeviceEndpoints.create());
    }

    @Override
    public Collection<IDevice> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IDevice> command = new GetCollectionCommand<>(context);
        return command.call(Device.class, DeviceEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public IDevice findByDeviceIdentification(String identification, ViewType viewType) throws ServiceException
    {
        GetCommand<IDevice> command = new GetCommand<>(context);
        return command.call(Device.class, DeviceEndpoints.findByDeviceIdentification(identification, viewType));
    }

    @Override
    public IDevice findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, DeviceEndpoints.findByUrn(urn, viewType), Device.class);
    }

    @Override
    public Collection<IDevice> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    @Override
    public IDevice findByDeviceIdentification(String identification) throws ServiceException
    {
        return findByDeviceIdentification(identification, ViewType.Standard);
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, DeviceEndpoints.update());
    }
}
