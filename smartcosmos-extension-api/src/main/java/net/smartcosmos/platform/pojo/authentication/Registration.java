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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.authentication.IRegistration;
import net.smartcosmos.platform.constraint.annotation.ValidRealm;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({"sendRegistrationEmail"})
public final class Registration extends DomainResource<IRegistration> implements IRegistration
{
    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    @Email
    @Size(max = IUser.EMAIL_ADDRESS_MAX_LENGTH)
    protected String emailAddress;

    @JsonView(JsonGenerationView.Minimum.class)
    @ValidRealm
    protected String realm;

    @JsonView(JsonGenerationView.Full.class)
    @Size(max = IRegistration.ADMIN_USER_URN_MAX_LENGTH)
    protected String adminUserUrn;

    @JsonView(JsonGenerationView.Standard.class)
    @Size(max = IRegistration.EMAIL_VERIFICATION_TOKEN_LENGTH)
    protected String emailVerificationToken;

    @JsonView(JsonGenerationView.Full.class)
    protected boolean emailVerified;

    @JsonView(JsonGenerationView.Full.class)
    protected long verificationTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

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
    public boolean isEmailVerified()
    {
        return emailVerified;
    }

    @Override
    public void setEmailVerified(boolean emailVerified)
    {
        this.emailVerified = emailVerified;
    }

    @Override
    public String getEmailVerificationToken()
    {
        return emailVerificationToken;
    }

    @Override
    public void setEmailVerificationToken(String emailVerificationToken)
    {
        this.emailVerificationToken = emailVerificationToken;
    }

    @Override
    public void setVerificationTimestamp(long verificationTimestamp)
    {
        this.verificationTimestamp = verificationTimestamp;
    }

    @Override
    public long getVerificationTimestamp()
    {
        return verificationTimestamp;
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
    public String getAdminUserUrn()
    {
        return adminUserUrn;
    }

    @Override
    public void setAdminUserUrn(String adminUserUrn)
    {
        this.adminUserUrn = adminUserUrn;
    }

    @Override
    public void consume(String verificationToken)
    {
        throw new UnsupportedOperationException("POJO instance cannot verify tokens; " +
                "only RegistrationEntity can verify token");
    }
}
