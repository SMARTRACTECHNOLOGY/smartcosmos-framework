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

import com.google.common.base.Preconditions;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.base.IAccountContext;
import com.snapbundle.model.base.IMoniker;
import com.snapbundle.model.base.IReferentialObject;
import com.snapbundle.model.context.IAccount;

abstract class AbstractReferentialBuilder<T extends IMoniker, E> extends AbstractMonikerBuilder<T, E>
{
    AbstractReferentialBuilder(T instance)
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
