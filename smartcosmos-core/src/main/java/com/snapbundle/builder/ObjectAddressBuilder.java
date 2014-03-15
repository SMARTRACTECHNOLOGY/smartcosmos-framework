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
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IObjectAddress;
import com.snapbundle.pojo.context.ObjectAddress;

public final class ObjectAddressBuilder extends AbstractMonikerBuilder<IObjectAddress, ObjectAddressBuilder>
{
    public ObjectAddressBuilder(IObject owner)
    {
        super(new ObjectAddress());
        Preconditions.checkNotNull(owner);
        instance.setObject(owner);
    }

    public ObjectAddressBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public ObjectAddressBuilder setLine1(String line1)
    {
        instance.setLine1(line1);
        return this;
    }

    public ObjectAddressBuilder setLine2(String line2)
    {
        instance.setLine2(line2);
        return this;
    }

    public ObjectAddressBuilder setCity(String city)
    {
        instance.setCity(city);
        return this;
    }

    public ObjectAddressBuilder setStateProvince(String stateProvince)
    {
        instance.setStateProvince(stateProvince);
        return this;
    }

    public ObjectAddressBuilder setPostalCode(String postalCode)
    {
        instance.setPostalCode(postalCode);
        return this;
    }

    public ObjectAddressBuilder setCountryAbbreviation(String countryAbbreviation)
    {
        instance.setCountryAbbreviation(countryAbbreviation);
        return this;
    }

    public ObjectAddressBuilder setRecordedTimestamp(long recordedTimestamp)
    {
        instance.setTimestamp(recordedTimestamp);
        return this;
    }
}
