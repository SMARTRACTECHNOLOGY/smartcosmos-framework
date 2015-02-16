package net.smartcosmos.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.model.base.IAccountContext;
import net.smartcosmos.model.base.IMoniker;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.context.IAccount;

public abstract class AbstractReferentialBuilder<T extends IMoniker, E> extends AbstractMonikerBuilder<T, E>
{
    protected AbstractReferentialBuilder(T instance)
    {
        super(instance);
    }

    public E setAccount(IAccount account)
    {
        ((IAccountContext) instance).setAccount(account);
        return (E) this;
    }

    public E setReferenceUrn(String urn)
    {
        Preconditions.checkNotNull(urn);
        ((IReferentialObject) instance).setReferenceUrn(urn);
        return (E) this;
    }

    public E setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        Preconditions.checkNotNull(entityReferenceType);
        ((IReferentialObject) instance).setEntityReferenceType(entityReferenceType);
        return (E) this;
    }
}
