package net.smartcosmos.platform.pojo.oauth;

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

import net.smartcosmos.model.extension.PermissionType;
import net.smartcosmos.platform.api.oauth.IOAuthTokenPermission;
import net.smartcosmos.platform.api.oauth.IOAuthTokenTransaction;
import net.smartcosmos.pojo.base.DomainResource;

public final class OAuthTokenPermission extends DomainResource<IOAuthTokenPermission> implements IOAuthTokenPermission
{
    private IOAuthTokenTransaction transaction;

    private PermissionType permissionType;

    @Override
    public PermissionType getPermissionType()
    {
        return permissionType;
    }

    @Override
    public void setPermissionType(PermissionType permissionType)
    {
        this.permissionType = permissionType;
    }

    @Override
    public IOAuthTokenTransaction getTransaction()
    {
        return transaction;
    }

    @Override
    public void setTransaction(IOAuthTokenTransaction transaction)
    {
        this.transaction = transaction;
    }

    @Override
    public void copy(IOAuthTokenPermission object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
