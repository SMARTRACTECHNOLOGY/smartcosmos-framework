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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.DeviceType;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class Device extends NamedObject<IDevice> implements IDevice
{
    @JsonView(JsonGenerationView.Standard.class)
    protected DeviceType deviceType;

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
    public DeviceType getDeviceType()
    {
        return deviceType;
    }

    @Override
    public void setDeviceType(DeviceType deviceType)
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
}
