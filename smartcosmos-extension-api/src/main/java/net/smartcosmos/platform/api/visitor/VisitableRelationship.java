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
import net.smartcosmos.objects.model.context.IRelationship;

public class VisitableRelationship extends AbstractVisitable<IRelationship> implements IRelationship
{
    public VisitableRelationship(EventType eventType, IRelationship instance)
    {
        super(eventType, instance);
    }

    @Override
    public String getRelatedReferenceUrn()
    {
        return instance.getReferenceUrn();
    }

    @Override
    public void setRelatedReferenceUrn(String urn)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public EntityReferenceType getRelatedEntityReferenceType()
    {
        return instance.getEntityReferenceType();
    }

    @Override
    public void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public boolean isReciprocal()
    {
        return instance.isReciprocal();
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
