package net.smartcosmos.client.impl.endpoint;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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


import net.smartcosmos.util.json.ViewType;

public final class ObjectEndpoints
{
    private ObjectEndpoints()
    {
    }

    private static final String BASE = "/objects";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String UPDATE__POST = BASE;

    private static final String QUERY_TYPE_AGNOSTIC__GET
            = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&objectUrnLike=%s&view=%s");

    private static final String QUERY_TYPE_SPECIFIC__GET
            = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&objectUrnLike=%s&objectType=%s&view=%s");

    private static final String FIND_BY_OBJECT_URN__GET
            = BASE.concat("/object/%s?exact=%s&view=%s");

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

    public static String findByExactObjectUrn(String objectUrn)
    {
        return findByExactObjectUrn(objectUrn, ViewType.Standard);
    }

    public static String findByExactObjectUrn(String objectUrn, ViewType viewType)
    {
        return String.format(FIND_BY_OBJECT_URN__GET, objectUrn, true, viewType);
    }

    public static String findByObjectUrnLike(String objectUrn)
    {
        return findByObjectUrnLike(objectUrn, ViewType.Standard);
    }

    public static String findByObjectUrnLike(String objectUrn, ViewType viewType)
    {
        return String.format(FIND_BY_OBJECT_URN__GET, objectUrn, false, viewType);
    }

    public static class Builder
    {
        long modifiedAfter = 0;

        String monikerLike = "*";

        String nameLike = "*";

        String objectUrnLike = "*";

        String objectType = null;

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

        public Builder objectUrnLike(String objectUrnLike)
        {
            this.objectUrnLike = objectUrnLike;
            return this;
        }

        public Builder type(String objectType)
        {
            this.objectType = objectType;
            return this;
        }

        public Builder view(ViewType view)
        {
            this.view = view;
            return this;
        }

        public String build()
        {
            if (objectType == null)
            {
                return String.format(QUERY_TYPE_AGNOSTIC__GET,
                        modifiedAfter,
                        monikerLike,
                        nameLike,
                        objectUrnLike,
                        view);
            } else
            {
                return String.format(QUERY_TYPE_SPECIFIC__GET,
                        modifiedAfter,
                        monikerLike,
                        nameLike,
                        objectUrnLike,
                        objectType,
                        view);
            }
        }
    }
}
