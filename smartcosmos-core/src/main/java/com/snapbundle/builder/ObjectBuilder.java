/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.builder;

import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IObject;
import com.snapbundle.pojo.context.ObjectImpl;

/**
 * Convenience Builder pattern class for creating new object instances.
 * <p/>
 * The minimum fields required to define a new Object are
 * <ul>
 * <li>ObjectURN</li>
 * <li>Name</li>
 * <li>Type</li>
 * </ul>
 */
public final class ObjectBuilder
{
    private IObject object;

    public ObjectBuilder(String objectUrn)
    {
        object = new ObjectImpl();
        object.setObjectUrn(objectUrn);
    }

    public ObjectBuilder setName(String name)
    {
        object.setName(name);
        return this;
    }

    public ObjectBuilder setDescription(String description)
    {
        object.setDescription(description);
        return this;

    }

    public ObjectBuilder setActive(boolean flag)
    {
        object.setActive(flag);
        return this;

    }

    public ObjectBuilder setAccount(IAccount account)
    {
        object.setAccount(account);
        return this;

    }

    public ObjectBuilder setMoniker(String moniker)
    {
        object.setMoniker(moniker);
        return this;

    }

    public ObjectBuilder setType(String type)
    {
        object.setType(type);
        return this;

    }

    public IObject build()
    {
        return object;
    }
}
