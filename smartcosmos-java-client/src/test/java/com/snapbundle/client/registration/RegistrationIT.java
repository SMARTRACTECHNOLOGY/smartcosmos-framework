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

package com.snapbundle.client.registration;

import com.snapbundle.client.ServiceException;

public class RegistrationIT
{
    public void checkRealmAvailability() throws ServiceException
    {
        IRegistrationClient client = RegistrationFactory.createRegistrationClient();
        boolean isAvailable = client.isRealmAvailable("trrllc.com");
        System.out.println("Available? " + isAvailable);
    }

    public void register() throws ServiceException
    {
        IRegistrationClient client = RegistrationFactory.createRegistrationClient();
        RegistrationResponse response = client.register("jason@trrllc.com");

        System.out.println("URN:   " + response.getUrn());
        System.out.println("Realm: " + response.getRealm());
    }

    public static void main(String[] args) throws ServiceException
    {
        RegistrationIT registrationIT = new RegistrationIT();
        registrationIT.checkRealmAvailability();
        registrationIT.register();
    }

}
