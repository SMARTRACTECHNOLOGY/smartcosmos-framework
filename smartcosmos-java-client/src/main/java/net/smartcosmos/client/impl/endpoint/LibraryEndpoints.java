package net.smartcosmos.client.impl.endpoint;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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


import net.smartcosmos.util.json.ViewType;

public final class LibraryEndpoints
{
    private LibraryEndpoints()
    {
    }

    private static final String BASE = "/library";

    private static final String CREATE_PUT = BASE;

    private static final String FIND_BY_URN_GET = BASE.concat("/%s?view=%s");

    private static final String UPDATE_POST = BASE;

    private static final String FIND_BY_NAME_LIKE_GET = BASE.concat("/?nameLike=%s&libraryElementType=%s");

    private static final String FIND_BY_NAME_AND_ELEMENT_TYPE_GET = BASE.concat("/%s/%s?view=%s");

    private static final String FIND_PARENT_GET = BASE.concat("/parent/%s?view=%s");

    private static final String FIND_CHILDREN_GET = BASE.concat("/children/%s?view=%s");

    private static final String QUERY_ELT_TYPE_AGNOSTIC_GET =
            BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&view=%s");

    private static final String QUERY_ELT_TYPE_SPECIFIC_GET =
            BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&libraryElementType=%s&view=%s");

    private static final String DELETE_DELETE = BASE;

    public static String create()
    {
        return CREATE_PUT;
    }

    public static String update()
    {
        return UPDATE_POST;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN_GET, urn, viewType);
    }

    public static String findByNameLike(String nameLike)
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    public static String findByNameLike(String nameLike, ViewType viewType)
    {
        return String.format(FIND_BY_NAME_LIKE_GET, nameLike, viewType);
    }

    public static String findByNameAndElementType(String name, String elementType, ViewType viewType)
    {
        return String.format(FIND_BY_NAME_AND_ELEMENT_TYPE_GET, name, elementType, viewType);
    }

    public static String getParent(String urn, ViewType viewType)
    {
        return String.format(FIND_PARENT_GET, urn, viewType);
    }

    public static String getChildren(String urn, ViewType viewType)
    {
        return String.format(FIND_CHILDREN_GET, urn, viewType);
    }

    public static String delete(String urn)
    {
        return String.format(DELETE_DELETE, urn);
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
                return String.format(QUERY_ELT_TYPE_AGNOSTIC_GET, modifiedAfter, monikerLike, nameLike, view);
            } else
            {
                return String.format(QUERY_ELT_TYPE_SPECIFIC_GET, modifiedAfter, monikerLike, nameLike,
                        libraryElementType, view);
            }
        }
    }
}
