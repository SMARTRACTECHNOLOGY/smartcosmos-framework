package net.smartcosmos.objects.model.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Foundational artifact within the SMART COSMOS Objects platform used to represent
 * nouns, e.g. people, bank accounts, vehicles, parks, buildings, etc.
 * <p/>
 * All objects are {@link net.smartcosmos.model.base.INamedObject} instances so
 * that a human-readable {@link net.smartcosmos.model.base.INamedObject#getName()}
 * can be assigned.
 * <p/>
 * This is the only class within the SMART COSMOS Objects platform that replaces the
 * system-assigned URN with an arbitrary developer-defined
 * {@link #getObjectUrn()}. This <code>objectUrn</code> must be unique within
 * the account context. It is up to the developer to devise a URN strategy that
 * meets this requirement.
 */
public interface IObject extends IAccountDomainResource<IObject>, INamedObject<IObject>, ITypedObject
{
    String getObjectUrn();

    void setObjectUrn(String urn);
}
