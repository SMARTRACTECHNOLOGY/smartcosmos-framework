package net.smartcosmos.objects.model.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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
 * The library element type is distinct from other {@link net.smartcosmos.model.base.INamedObject}
 * types in that the Type field is always set to "LibraryElement", and cannot be changed. The
 * field "LibraryElementType" is specific to LibraryElements, and is set to one of the values
 * specified in the LibraryHierarchy section of objects.yml.
 *
 */
public interface ILibraryElement extends IAccountDomainResource<ILibraryElement>, INamedObject<ILibraryElement>,
        ITypedObject
{
    void setName(String name);

    String getLibraryElementType();

    void setLibraryElementType(String libraryElementType);

    String getParentUrn();

    void setParentUrn(String parentUrn);
}
