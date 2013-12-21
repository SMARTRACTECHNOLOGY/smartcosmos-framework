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
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IObjectInteraction;
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.model.context.IUser;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class ObjectInteraction extends DomainResource<IObjectInteraction> implements IObjectInteraction
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = User.class)
    protected IUser user;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = Device.class)
    protected IDevice device;

    @JsonView(JsonGenerationView.Standard.class)
    protected String data;

    @JsonView(JsonGenerationView.Standard.class)
    protected long recordedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    protected long receivedTimestamp;

    @JsonView(JsonGenerationView.Standard.class)
    protected double lat;

    @JsonView(JsonGenerationView.Standard.class)
    protected double lon;

    @JsonView(JsonGenerationView.Standard.class)
    protected double alt;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean hasGeoLocation = false;

    @JsonView(JsonGenerationView.Minimum.class)
    protected boolean hasSessionMembership = false;

    @JsonView(JsonGenerationView.Standard.class)
    protected IObjectInteractionSession objectInteractionSession;

    @Override
    public boolean hasGeoLocation()
    {
        return hasGeoLocation;
    }

    @Override
    public void copy(IObjectInteraction objectInteraction)
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
    public IUser getUser()
    {
        return user;
    }

    @Override
    public void setUser(IUser user)
    {
        this.user = user;
    }

    @Override
    public IDevice getDevice()
    {
        return device;
    }

    @Override
    public void setDevice(IDevice device)
    {
        this.device = device;
    }

    @Override
    public String getData()
    {
        return data;
    }

    @Override
    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public long getRecordedTimestamp()
    {
        return recordedTimestamp;
    }

    @Override
    public void setRecordedTimestamp(long timestamp)
    {
        this.recordedTimestamp = timestamp;
    }

    @Override
    public long getReceivedTimestamp()
    {
        return receivedTimestamp;
    }

    @Override
    public void setReceivedTimestamp(long timestamp)
    {
        this.receivedTimestamp = timestamp;
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
    public boolean isSessionMember()
    {
        return hasSessionMembership;
    }

    public IObjectInteractionSession getObjectInteractionSession()
    {
        return objectInteractionSession;
    }

    public void setObjectInteractionSession(IObjectInteractionSession objectInteractionSession)
    {
        this.objectInteractionSession = objectInteractionSession;
        hasSessionMembership = (this.objectInteractionSession != null);
    }
}
