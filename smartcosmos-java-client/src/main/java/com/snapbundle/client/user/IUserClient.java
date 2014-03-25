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

import com.snapbundle.client.impl.IUpdateableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.context.IUser;
import com.snapbundle.util.json.ViewType;

public interface IUserClient extends IUpdateableBaseClient<IUser>
{
    IUser findByEmailAddress(String emailAddress) throws ServiceException;

    IUser findByEmailAddress(String emailAddress, ViewType viewType) throws ServiceException;

    void changePassword(String emailAddress, String newPassword) throws ServiceException;

    void resetPassword(String emailAddress) throws ServiceException;
}
