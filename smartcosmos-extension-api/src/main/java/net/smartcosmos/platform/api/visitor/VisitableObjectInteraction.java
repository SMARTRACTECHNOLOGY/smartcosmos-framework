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

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectInteraction;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;

public class VisitableObjectInteraction extends AbstractVisitable<IObjectInteraction> implements IObjectInteraction
{
    public VisitableObjectInteraction(EventType eventType, IObjectInteraction instance)
    {
        super(eventType, instance);
    }

    @Override
    public IObject getObject()
    {
        return new VisitableObject(eventType, instance.getObject());
    }

    @Override
    public void setObject(IObject object)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public long getRecordedTimestamp()
    {
        return instance.getRecordedTimestamp();
    }

    @Override
    public void setRecordedTimestamp(long timestamp)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public long getReceivedTimestamp()
    {
        return instance.getReceivedTimestamp();
    }

    @Override
    public void setReceivedTimestamp(long timestamp)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public boolean isSessionMember()
    {
        return instance.isSessionMember();
    }

    @Override
    public IObjectInteractionSession getObjectInteractionSession()
    {
        return instance.getObjectInteractionSession();
    }

    @Override
    public void setObjectInteractionSession(IObjectInteractionSession session)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public void setReferenceUrn(String urn)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public void setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getReferenceUrn()
    {
        return instance.getReferenceUrn();
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return instance.getEntityReferenceType();
    }

    @Override
    public String getType()
    {
        return instance.getType();
    }

    @Override
    public void setType(String type)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }
}
