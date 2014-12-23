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

public final class InteractionEndpoints
{
    private InteractionEndpoints()
    {
    }

    private static final String BASE = "/interactions";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String LIST_ALL__GET = BASE.concat("?view=%s");

    private static final String FIND_BY_OBJECT_URN__GET = BASE.concat("?objectUrn=%s&view=%s");

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String listAllInteractions()
    {
        return listAllInteractions(ViewType.Standard);
    }

    public static String listAllInteractions(ViewType viewType)
    {
        return String.format(LIST_ALL__GET, viewType);
    }

    public static String findByObjectUrn(String objectUrnLike)
    {
        return findByObjectUrn(objectUrnLike, ViewType.Standard);
    }

    public static String findByObjectUrn(String objectUrnLike, ViewType viewType)
    {
        return String.format(FIND_BY_OBJECT_URN__GET, objectUrnLike, viewType);
    }

}
