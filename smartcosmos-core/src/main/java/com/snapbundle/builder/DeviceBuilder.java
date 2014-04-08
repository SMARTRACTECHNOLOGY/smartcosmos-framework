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

package com.snapbundle.builder;

import com.google.common.base.Preconditions;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.pojo.context.Device;

/**
 * Convenience Builder pattern class for creating new {@link com.snapbundle.model.context.IDevice} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link com.snapbundle.Field#DEVICE_IDENTIFICATION_FIELD}</li>
 * </ul>
 */
public final class DeviceBuilder extends AbstractNamedObjectBuilder<IDevice, DeviceBuilder>
{
    public DeviceBuilder(String identification)
    {
        super(new Device());

        Preconditions.checkNotNull(identification);
        instance.setIdentification(identification);
    }

    public DeviceBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public DeviceBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }

}
