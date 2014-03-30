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

import com.snapbundle.client.connectivity.ServiceException;

/**
 * Public registration operations, including a realm availability check and new account creation.
 */
public interface IRegistrationClient
{
    /**
     * Determines if the specified realm is available for registration.
     *
     * @param realm Realm to check availability
     * @return true, if the realm is available for registration, or false if the realm has already been registered by
     * someone else
     * @throws ServiceException If the realm check failed for any reason
     */
    boolean isRealmAvailable(String realm) throws ServiceException;

    /**
     * Attempts to register a new account under the specified email address where the realm is dynamically extracted
     * from the domain name of the email address.
     *
     * @param emailAddress Email address to register the account under, where the domain name is dynamically
     *                     extracted to be the realm name
     * @return Registration response containing the account URN
     * @throws ServiceException If the registration failed for any reason
     */
    RegistrationResponse register(String emailAddress) throws ServiceException;

    /**
     * Attempts to register a new account under the specified email address using the explicitly provided realm.
     *
     * @param emailAddress Email address to register the account under
     * @param realm        Name of the realm
     * @return Registration response containing the account URN
     * @throws ServiceException If the registration failed for any reason
     */
    RegistrationResponse register(String emailAddress, String realm) throws ServiceException;
}
