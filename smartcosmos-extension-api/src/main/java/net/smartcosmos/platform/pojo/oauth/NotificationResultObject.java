package net.smartcosmos.platform.pojo.oauth;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.platform.api.oauth.INotificationResultObject;
import net.smartcosmos.pojo.base.DomainResource;

public final class NotificationResultObject<T extends IDomainResource>
        extends DomainResource<INotificationResultObject> implements INotificationResultObject<T>
{
    protected String result;

    protected EntityReferenceType entityReferenceType;

    protected T reference;

    public NotificationResultObject(EntityReferenceType entityReferenceType, T reference, String result)
    {
        this.entityReferenceType = entityReferenceType;
        this.result = result;
        this.reference = reference;
    }

    @Override
    public T getReference()
    {
        return reference;
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }

    @Override
    public String getResult()
    {
        return result;
    }

    @Override
    public void copy(INotificationResultObject object)
    {
        throw new UnsupportedOperationException("Notification results cannot be copied");
    }
}
