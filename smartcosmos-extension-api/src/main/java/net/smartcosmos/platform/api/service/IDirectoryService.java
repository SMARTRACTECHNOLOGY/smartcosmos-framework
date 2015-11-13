package net.smartcosmos.platform.api.service;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.api.Managed;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.authentication.ICredentials;
import net.smartcosmos.platform.api.authentication.IRegistration;

/**
 * Manage account registrations, defines new users, and allows an administrator to define a specific password or trigger
 * a password reset process via email.
 * <p>
 * The enabled (or disabled) status of a user is <b>not</b> managed by SMART COSMOS - it is by design managed by the
 * directory service itself.
 */
public interface IDirectoryService extends IService, IHealthCheckable, Managed
{
    /**
     * Defines a new directory based on a registration request. Registration services may be optionally disabled in
     * some SMART COSMOS platform services in the server's yml file.
     * <p>
     * All users are explicitly associated with a directory based on the email address.
     *
     * @param registration Registration request
     */
    void createDirectory(IRegistration registration);

    /**
     * Defines a new user and assigns them the specified role.
     *
     * @param user     User to create in the directory
     * @param roleType Well-defined role
     * @return randomly generated user password, preferably from a SHA1PRNG algorithm (or similar)
     * @throws java.lang.IllegalStateException when the service cannot use the user's field values to create a user
     */
    String createUser(IUser user, RoleType roleType);

    /**
     * Locate the single, assigned role of the specified user.
     *
     * @param user User to lookup role
     * @return role type assigned to the user at time of creation
     */
    RoleType lookupUserRole(IUser user);

    /**
     * Trigger a password reset process via the user's registered email address.
     *
     * @param user User to initiate an email password process against
     */
    void sendPasswordResetEmail(IUser user);

    /**
     * Assigns a specific password to the specified user. Each platform service requires the calling user be in the
     * {@link net.smartcosmos.model.context.RoleType#Administrator} role in order to invoke this method.
     *
     * @param user        User to assign a new password to
     * @param newPassword New password
     */
    void setPassword(IUser user, String newPassword);

    /**
     * Sets a new password on the user account if and only if the self-service password reset token is authenticated
     * as belonging to the specified user account. The self-service password reset token is generated by the
     * call to {@link #sendPasswordResetEmail(net.smartcosmos.model.context.IUser)}.
     *
     * @param user        User to assign the specified password to
     * @param resetToken  Reset token generated by {@link #sendPasswordResetEmail(net.smartcosmos.model.context.IUser)}
     * @param newPassword New password
     */
    void setPassword(IUser user, String resetToken, String newPassword);

    /**
     * Determines if the user is enabled by asking the directory service.
     *
     * @param user User to check if enabled
     * @return true, if the user is enabled
     */
    boolean isUserEnabled(IUser user);


    /**
     * Toggle the enabled / disabled status of the specified user.
     *
     * @param user User to toggle
     * @param flag true, if the user account is enabled, false if disabled
     */
    void setEnabled(IUser user, boolean flag);


    /**
     * Attempts to authenticate the user against the associated user's directory. Typically the user directory is
     * defined by extracting the realm from the user's email address, but this may vary in custom implementations.
     *
     * @param credentials Credentials passed by the container
     * @return Authenticated user
     * @throws java.lang.RuntimeException defined by the concrete class if the authentication fails for any reason
     */
    IAuthenticatedUser authenticate(ICredentials credentials);

    /**
     * Supports an OAuth 2.0 token exchange, automatically translating the OAuth 2 bearer access token to the physical
     * user's account that authorized the OAuth token.
     *
     * @param bearerAccessToken bearer access token
     * @return Authorizing user
     * @throws java.lang.RuntimeException defined by the concrete class if the exchange fails or OAuth is not supported.
     * @see net.smartcosmos.platform.api.authentication.IAuthenticatedUser#isOAuthAuthenticated()
     * @see net.smartcosmos.platform.api.authentication.IAuthenticatedUser#getExtendedUser()
     */
    IAuthenticatedUser exchangeToken(String bearerAccessToken);
}
