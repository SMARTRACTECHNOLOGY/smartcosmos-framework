package net.smartcosmos.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Core
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
import net.smartcosmos.objects.model.context.IObject;

/**
 * Created by asiegel on 26.10.2015.
 */
public final class ObjectBuilder extends AbstractNamedObjectBuilder<IObject, ObjectBuilder>
{
    public ObjectBuilder(IObject instance)
    {
        super(instance);
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

    public ObjectBuilder setObjectUrn(String objectUrn)
    {
        instance.setObjectUrn(objectUrn);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getObjectUrn(), "objectUrn must not be null");
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }
}
