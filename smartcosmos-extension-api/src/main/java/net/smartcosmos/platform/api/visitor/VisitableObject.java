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

import net.smartcosmos.model.event.EventType;
import net.smartcosmos.objects.model.context.IObject;

public class VisitableObject extends AbstractVisitable<IObject> implements IObject
{
    public VisitableObject(EventType eventType, IObject instance)
    {
        super(eventType, instance);
    }

    @Override
    public String getObjectUrn()
    {
        return instance.getObjectUrn();
    }

    @Override
    public void setObjectUrn(String urn)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getName()
    {
        return instance.getName();
    }

    @Override
    public void setName(String name)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public String getDescription()
    {
        return instance.getDescription();
    }

    @Override
    public void setDescription(String description)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
    }

    @Override
    public boolean isActive()
    {
        return instance.isActive();
    }

    @Override
    public void setActive(boolean flag)
    {
        throw new UnsupportedOperationException("operation is not supported in an IVisitable reference");
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
