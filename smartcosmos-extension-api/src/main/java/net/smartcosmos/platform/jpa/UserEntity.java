package net.smartcosmos.platform.jpa;

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

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.platform.jpa.base.DomainResourceAccountEntity;
import net.smartcosmos.util.json.JsonGenerationView;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "account_user")
public class UserEntity extends DomainResourceAccountEntity<IUser>implements IUser, Serializable
{
    private static final long serialVersionUID = -1564031691451114125L;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = EMAIL_ADDRESS_MAX_LENGTH, nullable = false, updatable = false)
    @Email
    @NotNull
    @Size(max = EMAIL_ADDRESS_MAX_LENGTH)
    protected String emailAddress;

    @JsonView(JsonGenerationView.Full.class)
    @Column(length = GIVEN_NAME_MAX_LENGTH, nullable = true)
    @Size(max = GIVEN_NAME_MAX_LENGTH)
    protected String givenName;

    @JsonView(JsonGenerationView.Full.class)
    @Column(length = SURNAME_MAX_LENGTH, nullable = true)
    @Size(max = SURNAME_MAX_LENGTH)
    protected String surname;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    protected RoleType roleType;

    @Override
    public String getEmailAddress()
    {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(final String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    @Override
    public RoleType getRoleType()
    {
        return roleType;
    }

    @Override
    public void setRoleType(final RoleType role)
    {
        this.roleType = role;
    }

    @Override
    public String getGivenName()
    {
        return givenName;
    }

    @Override
    public void setGivenName(final String givenName)
    {
        this.givenName = givenName;
    }

    @Override
    public String getSurname()
    {
        return surname;
    }

    @Override
    public void setSurname(final String surname)
    {
        this.surname = surname;
    }

    @Override
    public void copy(final IUser target)
    {
        super.copy(target);

        this.emailAddress = target.getEmailAddress();
        this.givenName = target.getGivenName();
        this.surname = target.getSurname();
        this.roleType = target.getRoleType();
    }
}
