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

public final class RegistrationEndpoints
{
    private RegistrationEndpoints()
    {
    }

    private static final String BASE = "/registration";

    private static final String CREATE_REGISTRATION_PUT = BASE.concat("/register");

    private static final String CHECK_REALM_AVAILABILITY_GET = BASE.concat("/realm/%s");

    private static final String CONFIRM_REGISTRATION_GET = BASE.concat("/confirm/%s/%s");

    public static String checkRealmAvailability(String realm)
    {
        return String.format(CHECK_REALM_AVAILABILITY_GET, realm);
    }

    public static String registration()
    {
        return CREATE_REGISTRATION_PUT;
    }

    public static String confirmRegistration(String emailVerificationToken, String emailAddress)
    {
        return String.format(CONFIRM_REGISTRATION_GET, emailVerificationToken, emailAddress);
    }
}
