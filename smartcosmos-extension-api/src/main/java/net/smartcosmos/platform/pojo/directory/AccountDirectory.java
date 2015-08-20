package net.smartcosmos.platform.pojo.directory;

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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.directory.IAccountDirectory;
import net.smartcosmos.pojo.base.DomainResource;

public final class AccountDirectory extends DomainResource<IAccountDirectory> implements IAccountDirectory
{
    protected String realm;

    protected String directoryHref;

    protected String adminGroupHref;

    protected String userGroupHref;

    protected IAccount account;

    @Override
    public String getRealm()
    {
        return realm;
    }

    @Override
    public void setRealm(String realm)
    {
        this.realm = realm;
    }

    @Override
    public String getDirectoryHref()
    {
        return directoryHref;
    }

    @Override
    public void setDirectoryHref(String directoryHref)
    {
        this.directoryHref = directoryHref;
    }

    @Override
    public String getAdminGroupHref()
    {
        return adminGroupHref;
    }

    @Override
    public void setAdminGroupHref(String adminGroupHref)
    {
        this.adminGroupHref = adminGroupHref;
    }

    @Override
    public String getUserGroupHref()
    {
        return userGroupHref;
    }

    @Override
    public void setUserGroupHref(String userGroupHref)
    {
        this.userGroupHref = userGroupHref;
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public void copy(IAccountDirectory iAccountDirectory)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
