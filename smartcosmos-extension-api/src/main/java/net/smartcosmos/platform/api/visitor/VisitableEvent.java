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

import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;

public class VisitableEvent extends AbstractVisitable<IEvent> implements IEvent
{
    public VisitableEvent(EventType eventType, IEvent instance)
    {
        super(eventType, instance);
    }

    @Override
    public EventType getEventType()
    {
        return instance.getEventType();
    }

    @Override
    public void setEventType(EventType eventType)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public IUser getUser()
    {
        return instance.getUser();
    }

    @Override
    public void setUser(IUser user)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getSource()
    {
        return instance.getSource();
    }

    @Override
    public void setSource(String source)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public boolean suppressBroadcast()
    {
        return instance.suppressBroadcast();
    }

    @Override
    public void setSuppressBroadcast(boolean flag)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }
}
