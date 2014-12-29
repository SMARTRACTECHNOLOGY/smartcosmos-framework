package net.smartcosmos.client.common.event;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.pojo.context.User;
import net.smartcosmos.util.json.JsonGenerationView;

public class DynamicEvent extends DomainResource<IEvent>
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
    protected JsonNode source;

    public IUser getUser()
    {
        return user;
    }

    public void setUser(IUser user)
    {
        this.user = user;
    }

    public EventType getEventType()
    {
        return eventType;
    }

    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    public JsonNode getSource()
    {
        return source;
    }

    public void setSource(JsonNode source)
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

        DynamicEvent event = (DynamicEvent) o;

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

