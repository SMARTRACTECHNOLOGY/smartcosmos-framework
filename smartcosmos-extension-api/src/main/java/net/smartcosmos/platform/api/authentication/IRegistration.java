package net.smartcosmos.platform.api.authentication;

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

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;

/**
 * Minimal set of POJO fields that support multi-tenant registration within a SMART COSMOS platform service. The
 * {@link #getRealm()} field is used to uniquely identify the <b>account holder</b> (i.e. a company), while the
 * {@link #getEmailAddress()} is used to uniquely identify the <b>administrator account</b> (i.e. a person).
 * <p/>
 * The registration process across all SMART COSMOS platform services inherently depend upon positive verification
 * of email address ownership. Therefore, the "registration" process effectively stalls until the email address
 * has been verified by clicking through the hyperlink in the welcome email.
 * <p/>
 * Only once the administrator account has been positively identified is the Account record actually activated.
 * <p/>
 * <b>NOTE:</b>Email addresses must be globally unique and are <i>not</i> namespaced within a specific realm!
 * <p/>
 * <b>For security reasons, the verification token is irreversibly scrambled by the platform service to ensure
 * that a registration can be positively acknowledged once and only once.</b>
 */
public interface IRegistration extends IDomainResource<IRegistration>
{
    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    String getAdminUserUrn();

    void setAdminUserUrn(String urn);

    String getRealm();

    void setRealm(String realm);

    String getEmailVerificationToken();

    void setEmailVerificationToken(String emailVerificationToken);

    boolean isEmailVerified();

    void setEmailVerified(boolean flag);

    long getVerificationTimestamp();

    void setVerificationTimestamp(long verificationTimestamp);

    IAccount getAccount();

    void setAccount(IAccount account);

    void copy(IRegistration registration);

    void consume(String verificationToken);
}
