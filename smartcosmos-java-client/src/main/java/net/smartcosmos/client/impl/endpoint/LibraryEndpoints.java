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

package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.objects.model.context.ILibraryElement;
import net.smartcosmos.util.json.ViewType;

public final class LibraryEndpoints
{
    private LibraryEndpoints()
    {
    }

    private static final String BASE = "/library";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String UPDATE__POST = BASE;

    private static final String FIND_BY_NAME_LIKE__GET = BASE.concat("/?nameLike=%s&libraryElementType=%s");

    private static final String FIND_BY_NAME_AND_ELEMENT_TYPE__GET = BASE.concat("/%s/%s?view=%s");

    private static final String FIND_PARENT__GET = BASE.concat("/parent/%s?view=%s");

    private static final String FIND_CHILDREN__GET = BASE.concat("/children/%s?view=%s");

    private static final String QUERY_ELT_TYPE_AGNOSTIC__GET = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&view=%s");

    private static final String QUERY_ELT_TYPE_SPECIFIC__GET = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&libraryElementType=%s&view=%s");

    private static final String DELETE__DELETE = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__POST;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByNameLike(String nameLike)
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    public static String findByNameLike(String nameLike, ViewType viewType)
    {
        return String.format(FIND_BY_NAME_LIKE__GET, nameLike, viewType);
    }

    public static String findByNameAndElementType(String name, String elementType, ViewType viewType)
    {
        return String.format(FIND_BY_NAME_AND_ELEMENT_TYPE__GET, name, elementType, viewType);
    }

    public static String getParent(String urn, ViewType viewType)
    {
        return String.format(FIND_PARENT__GET, urn, viewType);
    }

    public static String getChildren(String urn, ViewType viewType)
    {
        return String.format(FIND_CHILDREN__GET, urn, viewType);
    }

    public static String delete(String urn)
    {
        return String.format(DELETE__DELETE, urn);
    }

    public static class Builder
    {
        long modifiedAfter = 0;

        String monikerLike = "*";

        String nameLike = "*";

        String libraryElementType = "*";

        ViewType view = ViewType.Standard;

        public Builder()
        {

        }

        public Builder modifiedAfter(long timestamp)
        {
            modifiedAfter = timestamp;
            return this;
        }

        public Builder monikerLike(String monikerLike)
        {
            this.monikerLike = monikerLike;
            return this;
        }

        public Builder nameLike(String nameLike)
        {
            this.nameLike = nameLike;
            return this;
        }

        public Builder libraryElementType(String libraryElementType)
        {
            this.libraryElementType = libraryElementType;
            return this;
        }

        public Builder view(ViewType view)
        {
            this.view = view;
            return this;
        }

        public String build()
        {
            if (libraryElementType == null)
            {
                return String.format(QUERY_ELT_TYPE_AGNOSTIC__GET, modifiedAfter, monikerLike, nameLike, view);
            } else
            {
                return String.format(QUERY_ELT_TYPE_SPECIFIC__GET, modifiedAfter, monikerLike, nameLike, libraryElementType, view);
            }
        }
    }
}
