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

package com.snapbundle.client.device;

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.DeviceEndpoints;
import com.snapbundle.client.impl.AbstractUpdateableBaseClient;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.Device;
import com.snapbundle.util.json.ViewType;
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
    public Collection<IDevice> findByNameLike(String nameLike, ViewType viewType)
    {
        // TODO: find device with name like
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public IDevice findByDeviceIdentification(String identification, ViewType viewType)
    {
        // TODO: find device with identification
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public IDevice findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, viewType, DeviceEndpoints.findByUrn(urn, viewType), Device.class);
    }

    @Override
    public Collection<IDevice> findByNameLike(String nameLike)
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    @Override
    public IDevice findByDeviceIdentification(String identification)
    {
        return findByDeviceIdentification(identification, ViewType.Standard);
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, DeviceEndpoints.update());
    }
}
