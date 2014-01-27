/*
 * SnapBundle™ SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.model.extension;

import com.snapbundle.model.base.IDomainResource;
import com.snapbundle.model.base.INamedObject;
import com.snapbundle.model.context.IAccount;

public interface IExtension extends IDomainResource<IExtension>, INamedObject<IExtension>
{
    int getVersion();

    void setVersion(int version);

    IAccount getAccount();

    void setAccount(IAccount account);

    String getSupportEmail();

    void setSupportEmail(String supportEmail);

    String getWebSiteUrl();

    void setWebSiteUrl(String webSiteUrl);

    String getClientId();

    String getClientSecret();

    String getAppCatalogUrl();

    String getRedirectUrl();

    void setRedirectUrl(String redirectUrl);

    String getLongDescription();

    void setLongDescription(String longDescription);

    long getRegistrationTimestamp();

    ExtensionType getExtensionType();

    void setExtensionType(ExtensionType extensionType);
}
