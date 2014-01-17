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
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.model.context.ISessionType;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.util.json.JsonGenerationView;

public class ObjectInteractionSession extends NamedObject<IObjectInteractionSession> implements IObjectInteractionSession
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long startTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long stopTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = SessionType.class)
    protected ISessionType sessionType;

    public long getStartTimestamp()
    {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp)
    {
        this.startTimestamp = startTimestamp;
    }

    public long getStopTimestamp()
    {
        return stopTimestamp;
    }

    public void setStopTimestamp(long stopTimestamp)
    {
        this.stopTimestamp = stopTimestamp;
    }

    public ISessionType getSessionType()
    {
        return sessionType;
    }

    public void setSessionType(ISessionType sessionType)
    {
        this.sessionType = sessionType;
    }

    public IAccount getAccount()
    {
        return account;
    }

    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public void copy(IObjectInteractionSession object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectInteractionSession that = (ObjectInteractionSession) o;

        if (startTimestamp != that.startTimestamp) return false;
        if (stopTimestamp != that.stopTimestamp) return false;
        if (!account.equals(that.account)) return false;
        if (!sessionType.equals(that.sessionType)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + (int) (startTimestamp ^ (startTimestamp >>> 32));
        result = 31 * result + (int) (stopTimestamp ^ (stopTimestamp >>> 32));
        result = 31 * result + sessionType.hashCode();
        return result;
    }
}
