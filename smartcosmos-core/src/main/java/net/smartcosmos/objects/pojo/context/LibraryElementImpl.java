package net.smartcosmos.objects.pojo.context;

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

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.objects.model.context.ILibraryElement;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;


// JRW: No need for the Impl extension
public class LibraryElementImpl extends AccountTypedNamedObject<ILibraryElement> implements ILibraryElement
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String libraryElementType;

    // JRW: Why do we need this when it exists in the base class already?
    @JsonView(JsonGenerationView.Minimum.class)
    protected String name;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String parent;

    public String getLibraryElementType()
    {
        return libraryElementType;
    }

    public void setLibraryElementType(String libraryElementType)
    {
        this.libraryElementType = libraryElementType;
    }

    public String name()
    {
        return name;
    }

    public void name(String libraryElementType)
    {
        this.name = name;
    }

    @Override
    public String getParent()
    {
        return parent;
    }

    @Override
    public void setParent(String parent)
    {
        this.parent = parent;
    }

    public boolean equals(LibraryElementImpl o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LibraryElementImpl object = (LibraryElementImpl) o;

        if (!libraryElementType.equals(object.libraryElementType)) return false;
        if (!name.equals(object.name)) return false;
        if (!parent.equals(object.parent)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + name.hashCode() + libraryElementType.hashCode() + urn.hashCode();
        return result;
    }
}
