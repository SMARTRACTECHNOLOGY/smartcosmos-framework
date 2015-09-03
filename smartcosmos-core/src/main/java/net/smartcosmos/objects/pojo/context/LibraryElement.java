package net.smartcosmos.objects.pojo.context;

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

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.objects.model.context.ILibraryElement;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import static net.smartcosmos.Field.LIBRARY_ELEMENT_TYPE_NAME;

public class LibraryElement extends AccountTypedNamedObject<ILibraryElement> implements ILibraryElement
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String libraryElementType;

    @JsonView(JsonGenerationView.Minimum.class)
    private String parent;

    public String getLibraryElementType()
    {
        return libraryElementType;
    }

    public void setLibraryElementType(String libraryElementType)
    {
        this.libraryElementType = libraryElementType;
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

    @Override
    public void setType(String type)
    {
        this.type = LIBRARY_ELEMENT_TYPE_NAME;
    }

    public boolean equals(LibraryElement libraryElement)
    {
        if (this == libraryElement) return true;
        if (libraryElement == null || getClass() != libraryElement.getClass()) return false;
        if (!super.equals(libraryElement)) return false;

        if (!libraryElementType.equals(libraryElement.libraryElementType)) return false;
        if (!name.equals(libraryElement.name)) return false;
        if (!parent.equals(libraryElement.parent)) return false;

        return true;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LibraryElement other = (LibraryElement) obj;
        if (libraryElementType == null)
        {
            if (other.libraryElementType != null)
                return false;
        } else if (!libraryElementType.equals(other.libraryElementType))
            return false;
        if (parent == null)
        {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((libraryElementType == null) ? 0 : libraryElementType.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        return result;
    }
}
