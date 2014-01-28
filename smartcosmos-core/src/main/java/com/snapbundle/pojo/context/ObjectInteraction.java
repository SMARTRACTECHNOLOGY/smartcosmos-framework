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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IObjectInteraction;
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.json.JsonGenerationView;

public class ObjectInteraction extends ReferentialObject<IObjectInteraction> implements IObjectInteraction
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Standard.class)
    protected long recordedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    protected long receivedTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    protected boolean hasSessionMembership = false;

    @JsonView(JsonGenerationView.Standard.class)
    protected IObjectInteractionSession objectInteractionSession;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String type;

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        Preconditions.checkNotNull(type, "type must not be null");
        this.type = type;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectInteraction that = (ObjectInteraction) o;

        if (hasSessionMembership != that.hasSessionMembership) return false;
        if (receivedTimestamp != that.receivedTimestamp) return false;
        if (recordedTimestamp != that.recordedTimestamp) return false;
        if (!account.equals(that.account)) return false;
        if (!object.equals(that.object)) return false;
        if (objectInteractionSession != null ? !objectInteractionSession.equals(that.objectInteractionSession) : that.objectInteractionSession != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + object.hashCode();
        result = 31 * result + (int) (recordedTimestamp ^ (recordedTimestamp >>> 32));
        result = 31 * result + (int) (receivedTimestamp ^ (receivedTimestamp >>> 32));
        result = 31 * result + (hasSessionMembership ? 1 : 0);
        result = 31 * result + (objectInteractionSession != null ? objectInteractionSession.hashCode() : 0);
        return result;
    }
}
