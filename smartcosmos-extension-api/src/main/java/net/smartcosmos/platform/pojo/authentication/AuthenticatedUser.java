package net.smartcosmos.platform.pojo.authentication;

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
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.pojo.context.User;

public final class AuthenticatedUser extends User implements IAuthenticatedUser
{
    private final boolean oauthFlag;

    private final boolean basicFlag;

    private final IUser extensionUser;

    public AuthenticatedUser(IUser actualUser)
    {
        this(actualUser, null);
    }

    public AuthenticatedUser(IUser actualUser, IUser extensionUser)
    {
        this.basicFlag = (null == extensionUser);
        this.oauthFlag = (null != extensionUser);
        this.extensionUser = extensionUser;

        this.setUniqueId(actualUser.getUniqueId());
        this.setUrn(actualUser.getUrn());

        this.setMoniker(actualUser.getMoniker());

        this.setAccount(actualUser.getAccount());

        this.setGivenName(actualUser.getGivenName());
        this.setSurname(actualUser.getSurname());
        this.setEmailAddress(actualUser.getEmailAddress());

        this.setRoleType(actualUser.getRoleType());
    }

    @Override
    public boolean isOAuthAuthenticated()
    {
        return oauthFlag;
    }

    @Override
    public boolean isBasicAuthenticated()
    {
        return basicFlag;
    }

    @Override
    public IUser getExtendedUser()
    {
        return extensionUser;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AuthenticatedUser that = (AuthenticatedUser) o;

        if (basicFlag != that.basicFlag) return false;
        if (oauthFlag != that.oauthFlag) return false;
        if (extensionUser != null ? !extensionUser.equals(that.extensionUser) : that.extensionUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (oauthFlag ? 1 : 0);
        result = 31 * result + (basicFlag ? 1 : 0);
        result = 31 * result + (extensionUser != null ? extensionUser.hashCode() : 0);
        return result;
    }
}
