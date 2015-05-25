package net.smartcosmos.platform.base;

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
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.service.IVisitorService;

public abstract class AbstractVisitorService extends AbstractService implements IVisitorService
{
    protected final EntityReferenceType entityReferenceType;

    protected AbstractVisitorService(EntityReferenceType entityReferenceType,
                                     String serviceId,
                                     String name)
    {
        super(serviceId, name);
        this.entityReferenceType = entityReferenceType;
        Preconditions.checkNotNull(entityReferenceType, "entityReferenceType must not be null");
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }


    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {

    }
}

