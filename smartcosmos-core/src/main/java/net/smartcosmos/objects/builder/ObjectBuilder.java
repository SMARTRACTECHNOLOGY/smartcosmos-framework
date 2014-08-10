/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IObject} instances.
 * <p/>
 * The minimum fields required to define a new Object are
 * <ul>
 * <li>{@link net.smartcosmos.Field#OBJECT_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class ObjectBuilder extends AbstractNamedObjectBuilder<IObject, ObjectBuilder>
{
    public ObjectBuilder(String objectUrn)
    {
        super(new ObjectImpl());

        Preconditions.checkNotNull(objectUrn);
        instance.setObjectUrn(objectUrn);
    }

    public ObjectBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public ObjectBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }
}
