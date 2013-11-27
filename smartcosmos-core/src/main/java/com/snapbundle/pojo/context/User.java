/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IUser;
import com.snapbundle.model.context.RoleType;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.util.HashUtil;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class User extends DomainResource<IUser> implements IUser
{
    private static Logger logger = Logger.getLogger(User.class.getName());

    @JsonView(JsonGenerationView.Minimum.class)
    protected String emailAddress;

    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean activeFlag;

    @JsonView(JsonGenerationView.Standard.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Restricted.class)
    protected String passwordHash;

    @JsonView(JsonGenerationView.Minimum.class)
    protected RoleType roleType;

    public static IUser fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, User.class);
    }

    @Override
    public void copy(IUser user)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public String getEmailAddress()
    {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
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
    public IObject getAssociatedObject()
    {
        return object;
    }

    @Override
    public void setAssociatedObject(IObject object)
    {
        this.object = object;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public String getPasswordHash()
    {
        return passwordHash;
    }

    @Override
    public void createPasswordHash(char[] password, InputStream saltStream)
    {
        this.passwordHash = HashUtil.createHash(password, saltStream);
    }

    @Override
    public RoleType getRoleType()
    {
        return roleType;
    }

    @Override
    public void setRoleType(RoleType roleType)
    {
        this.roleType = roleType;
    }
}
