package net.smartcosmos.platform.api.authentication;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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
