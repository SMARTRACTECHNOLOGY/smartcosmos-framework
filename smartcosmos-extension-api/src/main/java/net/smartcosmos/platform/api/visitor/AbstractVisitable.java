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
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.event.EventType;

abstract class AbstractVisitable<T extends IAccountDomainResource>
        implements IAccountDomainResource<T>, IVisitable<T>
{
    protected final T instance;

    protected final EventType eventType;

    AbstractVisitable(EventType eventType, T instance)
    {
        Preconditions.checkNotNull(instance, "instance must not be null");
        this.instance = instance;

        Preconditions.checkNotNull(eventType, "eventType must not be null");
        this.eventType = eventType;
    }

    @Override
    public EventType getEventType()
    {
        return eventType;
    }

    @Override
    public String getUniqueId()
    {
        throw new UnsupportedOperationException("Unique ID is opaque and not supported in an IVisitable reference");
    }

    @Override
    public void setUniqueId(String s)
    {
        throw new UnsupportedOperationException("Unique ID is opaque and not supported in an IVisitable reference");
    }

    @Override
    public void copy(T target)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public IAccount getAccount()
    {
        return instance.getAccount();
    }

    @Override
    public void setAccount(IAccount iAccount)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getMoniker()
    {
        return instance.getMoniker();
    }

    @Override
    public void setMoniker(String s)
    {
        instance.setMoniker(s);
    }

    @Override
    public String getUrn()
    {
        return instance.getUrn();
    }

    @Override
    public void setUrn(String s)
    {
        instance.setUrn(s);
    }

    @Override
    public long getLastModifiedTimestamp()
    {
        return instance.getLastModifiedTimestamp();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void accept(IVisitor<T> visitor)
    {
        visitor.visit((T) this);
    }
}
