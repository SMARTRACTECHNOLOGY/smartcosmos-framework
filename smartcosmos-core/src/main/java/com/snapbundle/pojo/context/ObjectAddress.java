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
import com.snapbundle.model.context.AddressType;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IObjectAddress;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.util.json.JsonGenerationView;

public class ObjectAddress extends DomainResource<IObjectAddress> implements IObjectAddress
{
    @JsonView(JsonGenerationView.Standard.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Minimum.class)
    private AddressType addressType;

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

    @JsonView(JsonGenerationView.Standard.class)
    protected double lat;

    @JsonView(JsonGenerationView.Standard.class)
    protected double lon;

    @JsonView(JsonGenerationView.Standard.class)
    protected double alt;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean hasGeoLocation = false;

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
    public AddressType getAddressType()
    {
        return addressType;
    }

    @Override
    public void setAddressType(AddressType addressType)
    {
        this.addressType = addressType;
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
    public boolean hasGeoLocation()
    {
        return hasGeoLocation;
    }

    @Override
    public void setLat(double lat)
    {
        this.hasGeoLocation = true;
        this.lat = lat;
    }

    @Override
    public void setLon(double lon)
    {
        this.hasGeoLocation = true;
        this.lon = lon;
    }

    @Override
    public void setAlt(double alt)
    {
        this.hasGeoLocation = true;
        this.alt = alt;
    }

    @Override
    public double getLat()
    {
        return lat;
    }

    @Override
    public double getLon()
    {
        return lon;
    }

    @Override
    public double getAlt()
    {
        return alt;
    }

    @Override
    public void copy(IObjectAddress objectAddress)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
