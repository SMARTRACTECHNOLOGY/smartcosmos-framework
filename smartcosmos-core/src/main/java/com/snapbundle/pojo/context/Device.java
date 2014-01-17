/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.model.context.IDeviceType;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.util.json.JsonGenerationView;

public class Device extends NamedObject<IDevice> implements IDevice
{
    @JsonView(JsonGenerationView.Standard.class)
    @JsonDeserialize(as = DeviceType.class)
    protected IDeviceType deviceType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String identification;

    @JsonDeserialize(as = Account.class)
    @JsonView(JsonGenerationView.Full.class)
    protected IAccount account;

    @Override
    public void copy(IDevice device)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public IDeviceType getDeviceType()
    {
        return deviceType;
    }

    @Override
    public void setDeviceType(IDeviceType deviceType)
    {
        this.deviceType = deviceType;
    }

    @Override
    public String getIdentification()
    {
        return identification;
    }

    @Override
    public void setIdentification(String identification)
    {
        this.identification = identification;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Device device = (Device) o;

        if (!account.equals(device.account)) return false;
        if (!deviceType.equals(device.deviceType)) return false;
        if (!identification.equals(device.identification)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + deviceType.hashCode();
        result = 31 * result + identification.hashCode();
        result = 31 * result + account.hashCode();
        return result;
    }
}
