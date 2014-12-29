package net.smartcosmos.objects.pojo.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.base.ITypedObject;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class ObjectAddress extends DomainResource<IObjectAddress> implements IObjectAddress, ITypedObject
{
    @JsonView(JsonGenerationView.Standard.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String type;

    @JsonView(JsonGenerationView.Minimum.class)
    private String line1;

    @JsonView(JsonGenerationView.Minimum.class)
    private String line2;

    @JsonView(JsonGenerationView.Minimum.class)
    private String city;

    @JsonView(JsonGenerationView.Minimum.class)
    private String stateProvince;

    @JsonView(JsonGenerationView.Minimum.class)
    private String postalCode;

    @JsonView(JsonGenerationView.Minimum.class)
    private String countryAbbreviation;

    @JsonView(JsonGenerationView.Standard.class)
    private long timestamp;

    @Override
    public IObject getObject()
    {
        return object;
    }

    @Override
    public void setObject(IObject object)
    {
        this.object = object;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String getLine1()
    {
        return line1;
    }

    @Override
    public void setLine1(String line1)
    {
        this.line1 = line1;
    }

    @Override
    public String getLine2()
    {
        return line2;
    }

    @Override
    public void setLine2(String line2)
    {
        this.line2 = line2;
    }

    @Override
    public String getCity()
    {
        return city;
    }

    @Override
    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public String getStateProvince()
    {
        return stateProvince;
    }

    @Override
    public void setStateProvince(String stateProvince)
    {
        this.stateProvince = stateProvince;
    }

    @Override
    public String getPostalCode()
    {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @Override
    public String getCountryAbbreviation()
    {
        return countryAbbreviation;
    }

    @Override
    public void setCountryAbbreviation(String countryAbbreviation)
    {
        this.countryAbbreviation = countryAbbreviation;
    }

    @Override
    public long getTimestamp()
    {
        return timestamp;
    }

    @Override
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public IAccount getAccount()
    {
        return object.getAccount();
    }

    @Override
    public void setAccount(IAccount account)
    {
        throw new IllegalStateException("Object Address account is inferred through it's parent Object");
    }

    @Override
    public void copy(IObjectAddress objectAddress)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectAddress that = (ObjectAddress) o;

        if (timestamp != that.timestamp) return false;
        if (!type.equals(that.type)) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (countryAbbreviation != null
                ? !countryAbbreviation.equals(that.countryAbbreviation)
                : that.countryAbbreviation != null)
        {
            return false;
        }
        if (line1 != null ? !line1.equals(that.line1) : that.line1 != null) return false;
        if (line2 != null ? !line2.equals(that.line2) : that.line2 != null) return false;
        if (!object.equals(that.object)) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (stateProvince != null ? !stateProvince.equals(that.stateProvince) : that.stateProvince != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + object.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (line1 != null ? line1.hashCode() : 0);
        result = 31 * result + (line2 != null ? line2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (stateProvince != null ? stateProvince.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (countryAbbreviation != null ? countryAbbreviation.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
