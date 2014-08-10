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


import net.smartcosmos.util.json.ViewType;

public final class AccountEndpoints
{
    private AccountEndpoints()
    {
    }

    private static final String BASE = "/account";

    private static final String VIEW__GET = BASE.concat("?view=%s");

    private static final String CHANGE_MY_PASSWORD__POST = BASE.concat("/password/change");

    private static final String RESET_MY_PASSWORD__POST = BASE.concat("/password/reset");

    public static String view()
    {
        return view(ViewType.Standard);
    }

    public static String view(ViewType viewType)
    {
        return String.format(VIEW__GET, viewType);
    }

    public static String changeMyPassword()
    {
        return String.format(CHANGE_MY_PASSWORD__POST);
    }

    public static String resetMyPassword()
    {
        return RESET_MY_PASSWORD__POST;
    }
}
