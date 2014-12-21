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

package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.pojo.context.User;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.context.IUser} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#EMAIL_ADDRESS_FIELD}</li>
 * </ul>
 */
public final class UserBuilder extends AbstractMonikerBuilder<IUser, UserBuilder>
{
    public UserBuilder(String emailAddress)
    {
        super(new User());

        Preconditions.checkNotNull(emailAddress);
        instance.setEmailAddress(emailAddress);
        instance.setRoleType(RoleType.User);
    }

    public UserBuilder setGivenName(String givenName)
    {
        instance.setGivenName(givenName);
        return this;
    }

    public UserBuilder setSurname(String surname)
    {
        instance.setSurname(surname);
        return this;
    }

    public UserBuilder setRoleType(RoleType roleType)
    {
        instance.setRoleType(roleType);
        return this;
    }
}
