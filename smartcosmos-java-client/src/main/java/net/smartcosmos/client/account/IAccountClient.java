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

package net.smartcosmos.client.account;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.util.json.ViewType;

/**
 * Provides the ability to view your account configuration and change or reset your account password.
 */
public interface IAccountClient
{
    /**
     * Fetches your account using a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @return your account
     * @throws ServiceException
     */
    IAccount fetch() throws ServiceException;

    /**
     * Fetches your account using the specified field verbosity.
     *
     * @param viewType Field verbosity
     * @return your account
     * @throws ServiceException
     */
    IAccount fetch(ViewType viewType) throws ServiceException;

    /**
     * Changes your existing password to a new password.
     *
     * @param oldPassword Current password
     * @param newPassword New password
     * @return true, if the password was changed successfully
     * @throws ServiceException
     */
    boolean changePassword(String oldPassword, String newPassword) throws ServiceException;

    /**
     * Initiates a password reset workflow on the {@link net.smartcosmos.model.context.IUser} account represented
     * by the specified email address.
     *
     * @param emailAddress email address of the user account to initiate a password reset workflow
     * @throws ServiceException
     */
    void resetPassword(String emailAddress) throws ServiceException;
}
