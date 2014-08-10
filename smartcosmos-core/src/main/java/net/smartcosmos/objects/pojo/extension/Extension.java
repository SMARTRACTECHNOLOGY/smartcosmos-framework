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

package net.smartcosmos.objects.pojo.extension;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.extension.ExtensionType;
import net.smartcosmos.objects.model.extension.IExtension;
import net.smartcosmos.pojo.base.NamedObject;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;

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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Extension extension = (Extension) o;

        if (registrationTimestamp != extension.registrationTimestamp) return false;
        if (version != extension.version) return false;
        if (!account.equals(extension.account)) return false;
        if (appCatalogUrl != null ? !appCatalogUrl.equals(extension.appCatalogUrl) : extension.appCatalogUrl != null)
            return false;
        if (!clientId.equals(extension.clientId)) return false;
        if (!clientSecret.equals(extension.clientSecret)) return false;
        if (extensionType != extension.extensionType) return false;
        if (longDescription != null ? !longDescription.equals(extension.longDescription) : extension.longDescription != null)
            return false;
        if (!redirectUrl.equals(extension.redirectUrl)) return false;
        if (supportEmail != null ? !supportEmail.equals(extension.supportEmail) : extension.supportEmail != null)
            return false;
        if (webSiteUrl != null ? !webSiteUrl.equals(extension.webSiteUrl) : extension.webSiteUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (supportEmail != null ? supportEmail.hashCode() : 0);
        result = 31 * result + (webSiteUrl != null ? webSiteUrl.hashCode() : 0);
        result = 31 * result + clientId.hashCode();
        result = 31 * result + clientSecret.hashCode();
        result = 31 * result + redirectUrl.hashCode();
        result = 31 * result + (appCatalogUrl != null ? appCatalogUrl.hashCode() : 0);
        result = 31 * result + (longDescription != null ? longDescription.hashCode() : 0);
        result = 31 * result + (extensionType != null ? extensionType.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (int) (registrationTimestamp ^ (registrationTimestamp >>> 32));
        return result;
    }
}
