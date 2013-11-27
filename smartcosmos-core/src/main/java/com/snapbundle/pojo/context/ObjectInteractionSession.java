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
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.model.context.IUser;
import com.snapbundle.model.context.SessionType;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class ObjectInteractionSession extends NamedObject<IObjectInteractionSession> implements IObjectInteractionSession
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = User.class)
    protected IUser user;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long startTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long stopTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    protected SessionType sessionType;

    public static IObjectInteractionSession fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ObjectInteractionSession.class);
    }

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

    public SessionType getSessionType()
    {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType)
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

    public IUser getUser()
    {
        return user;
    }

    public void setUser(IUser user)
    {
        this.user = user;
    }

    @Override
    public void copy(IObjectInteractionSession object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
