/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

package net.smartcosmos.model.context;
import net.smartcosmos.model.base.IAccountDomainResource;

/**
 * System user with login and query privileges. Users are typically
 * not considered part of the contextual model captured by the system.
 * <p/>
 * SMART COSMOS Objects is <i>not a directory services</i> platform, so the constituent
 * parts of a user are relatively simplistic. Directory services are instead
 * a delegated operation based on the configuration of the platform as a whole.
 */
public interface IUser extends IAccountDomainResource<IUser>
{
    String getGivenName();

    void setGivenName(String givenName);

    String getSurname();

    void setSurname(String surname);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    RoleType getRoleType();

    void setRoleType(RoleType role);

    void copy(IUser user);
}
