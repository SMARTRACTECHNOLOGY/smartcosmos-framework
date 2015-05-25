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

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;

public class VisitableEvent implements IEvent, IVisitable<IEvent>
{
    protected final IEvent event;

    public VisitableEvent(IEvent event)
    {
        this.event = event;
        Preconditions.checkNotNull(event, "event must not be null");
    }

    @Override
    public EventType getEventType()
    {
        return event.getEventType();
    }

    @Override
    public void setEventType(EventType eventType)
    {
        event.setEventType(eventType);
    }

    @Override
    public IAccount getAccount()
    {
        return event.getAccount();
    }

    @Override
    public void setAccount(IAccount iAccount)
    {
        throw new UnsupportedOperationException("Security restrictions prevent the re-assignment of the event account");
    }

    @Override
    public IUser getUser()
    {
        return event.getUser();
    }

    @Override
    public void setUser(IUser user)
    {
        event.setUser(user);
    }

    @Override
    public String getSource()
    {
        return event.getSource();
    }

    @Override
    public void setSource(String source)
    {
        event.setSource(source);
    }

    @Override
    public boolean suppressBroadcast()
    {
        return event.suppressBroadcast();
    }

    @Override
    public void setSuppressBroadcast(boolean flag)
    {
        event.setSuppressBroadcast(flag);
    }

    @Override
    public String getUniqueId()
    {
        throw new UnsupportedOperationException("Unique ID is an opaque field; call getUrn() instead for event key");
    }

    @Override
    public void setUniqueId(String s)
    {
        throw new UnsupportedOperationException("Unique ID is opaquely assigned and cannot be manually set");
    }

    @Override
    public void copy(IEvent iEvent)
    {
        throw new UnsupportedOperationException("copy() is not supported");
    }

    @Override
    public String getMoniker()
    {
        return event.getMoniker();
    }

    @Override
    public void setMoniker(String moniker)
    {
        event.setMoniker(moniker);
    }

    @Override
    public String getUrn()
    {
        return event.getUrn();
    }

    @Override
    public void setUrn(String urn)
    {
        event.setUrn(urn);
    }

    @Override
    public long getLastModifiedTimestamp()
    {
        return event.getLastModifiedTimestamp();
    }

    @Override
    public void accept(IVisitor<IEvent> visitor)
    {
        visitor.visit(this);
    }
}
