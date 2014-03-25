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

package com.snapbundle.client.impl.endpoint;

import com.snapbundle.util.json.ViewType;

public final class UserEndpoints
{
    private UserEndpoints()
    {
    }

    private static final String BASE = "/users";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_EMAIL__GET = BASE.concat("/user/%s?view=%s");

    private static final String MANAGE_PASSWORD__POST = BASE.concat("/user");

    private static final String UPDATE__POST = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__POST;
    }

    public static String managePassword()
    {
        return MANAGE_PASSWORD__POST;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByEmailAddress(String emailAddress)
    {
        return findByEmailAddress(emailAddress, ViewType.Standard);
    }

    public static String findByEmailAddress(String emailAddress, ViewType viewType)
    {
        return String.format(FIND_BY_EMAIL__GET, emailAddress, viewType);
    }
}
