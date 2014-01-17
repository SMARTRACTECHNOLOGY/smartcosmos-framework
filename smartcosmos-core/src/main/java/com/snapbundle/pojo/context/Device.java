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
import com.snapbundle.model.context.IDevice;
import com.snapbundle.pojo.base.AccountTypedNamedObject;
import com.snapbundle.util.json.JsonGenerationView;

public class Device extends AccountTypedNamedObject<IDevice> implements IDevice
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String identification;

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

        if (!type.equals(device.type)) return false;
        if (!identification.equals(device.identification)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + identification.hashCode();
        return result;
    }
}
