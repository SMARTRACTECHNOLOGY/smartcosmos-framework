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

public final class ObjectAddressEndpoints
{
    private ObjectAddressEndpoints()
    {
    }

    private static final String BASE = "/objects/object/%s/address";

    private static final String CREATE__PUT = BASE;

    private static final String UPDATE__POST = BASE.concat("/%s");

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String DELETE__DELETE = BASE.concat("/%s");

    private static final String FIND_LAST_N__GET = BASE.concat("?count=%d&view=%s");

    public static String create(String objectUrn)
    {
        return String.format(CREATE__PUT, objectUrn);
    }

    public static String delete(String objectUrn, String urn)
    {
        return String.format(DELETE__DELETE, objectUrn, urn);
    }

    public static String update(String objectUrn, String urn)
    {
        return String.format(UPDATE__POST, objectUrn, urn);
    }

    public static String findByUrn(String objectUrn, String urn)
    {
        return findByUrn(objectUrn, urn, ViewType.Standard);
    }

    public static String findByUrn(String objectUrn, String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, objectUrn, urn, viewType);
    }

    public static String findLast(String objectUrn, int count)
    {
        return findLast(objectUrn, count, ViewType.Standard);
    }

    public static String findLast(String objectUrn, int count, ViewType viewType)
    {
        return String.format(FIND_LAST_N__GET, objectUrn, count, viewType);
    }

}
