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

public final class RegistrationEndpoints
{
    private RegistrationEndpoints()
    {
    }

    private static final String BASE = "/registration";

    private static final String CREATE_REGISTRATION__PUT = BASE.concat("/register");

    private static final String CHECK_REALM_AVAILABILITY__GET = BASE.concat("/realm/%s");

    private static final String CONFIRM_REGISTRATION__GET = BASE.concat("/confirm/%s/%s");

    public static String checkRealmAvailability(String realm)
    {
        return String.format(CHECK_REALM_AVAILABILITY__GET, realm);
    }

    public static String registration()
    {
        return CREATE_REGISTRATION__PUT;
    }

    public static String confirmRegistration(String emailVerificationToken, String emailAddress)
    {
        return String.format(CONFIRM_REGISTRATION__GET, emailVerificationToken, emailAddress);
    }
}
