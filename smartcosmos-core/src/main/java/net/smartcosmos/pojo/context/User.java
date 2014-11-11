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

package net.smartcosmos.pojo.context;

import java.util.ArrayList;
import java.util.List;

import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonFilter("userFilter")
public class User extends DomainResource<IUser> implements IUser
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String emailAddress;

    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

    @JsonView(JsonGenerationView.Full.class)
    private String givenName;

    @JsonView(JsonGenerationView.Full.class)
    private String surname;

    @JsonView(JsonGenerationView.Minimum.class)
    protected RoleType roleType;
    
    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = List.class)
    protected List<IGroup> groups= new ArrayList<>();

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
    public String getGivenName()
    {
        return givenName;
    }

    @Override
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    @Override
    public String getSurname()
    {
        return surname;
    }

    @Override
    public void setSurname(String surname)
    {
        this.surname = surname;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!account.equals(user.account)) return false;
        if (!emailAddress.equals(user.emailAddress)) return false;
        if (givenName != null ? !givenName.equals(user.givenName) : user.givenName != null) return false;
        if (object != null ? !object.equals(user.object) : user.object != null) return false;
        if (roleType != user.roleType) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + roleType.hashCode();
        return result;
    }

	@Override
	public List<IGroup> getGroups() {
		return groups;
	}

	@Override
	public void setGroups(List<IGroup> groups) {
		this.groups=groups;
	}

	
}
