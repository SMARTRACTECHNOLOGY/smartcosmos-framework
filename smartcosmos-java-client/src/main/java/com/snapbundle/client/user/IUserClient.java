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

package com.snapbundle.client.user;

import com.snapbundle.client.connectivity.ServiceException;
import com.snapbundle.client.impl.IUpdateableBaseClient;
import com.snapbundle.model.context.IUser;
import com.snapbundle.util.json.ViewType;

/**
 * Defines, updates or queries for {@link com.snapbundle.model.context.IUser} instances.
 */
public interface IUserClient extends IUpdateableBaseClient<IUser>
{
    /**
     * Locates the user record associated with the specified email address using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param emailAddress Email address of the user to locate
     * @return User record
     * @throws ServiceException if the email address does not exist
     */
    IUser findByEmailAddress(String emailAddress) throws ServiceException;

    /**
     * Locates the user record associated with the specified email address using the specified field verbosity.
     *
     * @param emailAddress Email address of the user to locate
     * @param viewType     Field verbosity
     * @return User record
     * @throws ServiceException if the email address does not exist
     */
    IUser findByEmailAddress(String emailAddress, ViewType viewType) throws ServiceException;

    /**
     * Assigns a new password to the user associated with the specified email address <b>so long as the
     * call is assigned the {@link com.snapbundle.model.context.RoleType#Administrator}</b> role.
     *
     * @param emailAddress Email address of the user to locate
     * @param newPassword  New password to assign
     * @throws ServiceException
     */
    void changePassword(String emailAddress, String newPassword) throws ServiceException;

    /**
     * Initiates a password reset workflow for the user associated with the specified email address <b>so long as the
     * call is assigned the {@link com.snapbundle.model.context.RoleType#Administrator}</b> role.
     *
     * @param emailAddress Email address of the user to initiate a password reset workflow on
     * @throws ServiceException
     */
    void resetPassword(String emailAddress) throws ServiceException;
}
