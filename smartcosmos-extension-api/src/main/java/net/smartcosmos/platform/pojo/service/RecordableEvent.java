package net.smartcosmos.platform.pojo.service;

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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;

public final class RecordableEvent
{
    private EventType eventType;

    private IAccount account;

    private IUser user;

    private Object source;

    public RecordableEvent(EventType eventType)
    {
        this.eventType = eventType;
    }

    public EventType getEventType()
    {
        return eventType;
    }

    public IAccount getAccount()
    {
        return account;
    }

    public RecordableEvent setAccount(IAccount account)
    {
        this.account = account;
        return this;
    }

    public IUser getUser()
    {
        return user;
    }

    public RecordableEvent setUser(IUser user)
    {
        this.user = user;
        return this;
    }

    public Object getSource()
    {
        return source;
    }

    public RecordableEvent setSource(Object source)
    {
        this.source = source;
        return this;
    }

    @Override
    public String toString()
    {
        return "RecordableEvent{" +
                "eventType=" + eventType +
                ", account=" + account +
                ", user=" + user +
                ", source=" + source +
                '}';
    }
}
