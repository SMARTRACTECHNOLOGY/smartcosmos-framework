/*
 * SnapBundle SDK
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

package com.snapbundle.pojo.extension;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.extension.ExtensionType;
import com.snapbundle.model.extension.IExtension;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonGenerationView;

public class Extension extends NamedObject<IExtension> implements IExtension
{
    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Standard.class)
    protected String supportEmail;

    @JsonView(JsonGenerationView.Published.class)
    protected String webSiteUrl;

    @JsonView(JsonGenerationView.Full.class)
    protected String clientId;

    @JsonView(JsonGenerationView.Full.class)
    protected String clientSecret;

    @JsonView(JsonGenerationView.Full.class)
    protected String redirectUrl;

    @JsonView(JsonGenerationView.Published.class)
    protected String appCatalogUrl;

    @JsonView(JsonGenerationView.Standard.class)
    protected String longDescription;

    @JsonView(JsonGenerationView.Published.class)
    protected ExtensionType extensionType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected int version;

    @JsonView(JsonGenerationView.Restricted.class)
    protected long registrationTimestamp;

    @Override
    public int getVersion()
    {
        return version;
    }

    @Override
    public void setVersion(int version)
    {
        this.version = version;
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
    public String getSupportEmail()
    {
        return supportEmail;
    }

    @Override
    public void setSupportEmail(String supportEmail)
    {
        this.supportEmail = supportEmail;
    }

    @Override
    public String getWebSiteUrl()
    {
        return webSiteUrl;
    }

    @Override
    public void setWebSiteUrl(String webSiteUrl)
    {
        this.webSiteUrl = webSiteUrl;
    }

    @Override
    public String getClientId()
    {
        return clientId;
    }

    @Override
    public String getClientSecret()
    {
        return clientSecret;
    }

    @Override
    public String getAppCatalogUrl()
    {
        return appCatalogUrl;
    }

    @Override
    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    @Override
    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String getLongDescription()
    {
        return longDescription;
    }

    @Override
    public void setLongDescription(String longDescription)
    {
        this.longDescription = longDescription;
    }

    @Override
    public ExtensionType getExtensionType()
    {
        return extensionType;
    }

    @Override
    public void setExtensionType(ExtensionType extensionType)
    {
        this.extensionType = extensionType;
    }

    @Override
    public long getRegistrationTimestamp()
    {
        return registrationTimestamp;
    }

    @Override
    public void copy(IExtension object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
