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

package com.snapbundle.pojo.event;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IUser;
import com.snapbundle.model.event.EventType;
import com.snapbundle.model.event.IEvent;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.pojo.context.User;
import com.snapbundle.util.json.JsonGenerationView;

public class Event extends DomainResource<IEvent> implements IEvent
{
    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = User.class)
    protected IUser user;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    protected EventType eventType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String source;

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
    public EventType getEventType()
    {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    @Override
    public String getSource()
    {
        return source;
    }

    @Override
    public void setSource(String source)
    {
        this.source = source;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Event event = (Event) o;

        if (account != null ? !account.equals(event.account) : event.account != null) return false;
        if (eventType != event.eventType) return false;
        if (source != null ? !source.equals(event.source) : event.source != null) return false;
        if (user != null ? !user.equals(event.user) : event.user != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + eventType.hashCode();
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
}
