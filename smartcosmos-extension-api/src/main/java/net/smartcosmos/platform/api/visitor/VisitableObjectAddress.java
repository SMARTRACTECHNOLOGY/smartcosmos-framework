package net.smartcosmos.platform.api.visitor;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.model.event.EventType;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectAddress;

public class VisitableObjectAddress extends AbstractVisitable<IObjectAddress> implements IObjectAddress
{
    public VisitableObjectAddress(EventType eventType, IObjectAddress instance)
    {
        super(eventType, instance);
    }

    @Override
    public IObject getObject()
    {
        return new VisitableObject(eventType, instance.getObject());
    }

    @Override
    public void setObject(IObject object)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getLine1()
    {
        return instance.getLine1();
    }

    @Override
    public void setLine1(String line1)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getLine2()
    {
        return instance.getLine2();
    }

    @Override
    public void setLine2(String line2)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getCity()
    {
        return instance.getCity();
    }

    @Override
    public void setCity(String city)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getStateProvince()
    {
        return instance.getStateProvince();
    }

    @Override
    public void setStateProvince(String stateProvince)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getPostalCode()
    {
        return instance.getPostalCode();
    }

    @Override
    public void setPostalCode(String postalCode)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getCountryAbbreviation()
    {
        return instance.getCountryAbbreviation();
    }

    @Override
    public void setCountryAbbreviation(String countryAbbreviation)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public long getTimestamp()
    {
        return instance.getTimestamp();
    }

    @Override
    public void setTimestamp(long timestamp)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getType()
    {
        return instance.getType();
    }

    @Override
    public void setType(String type)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }
}
