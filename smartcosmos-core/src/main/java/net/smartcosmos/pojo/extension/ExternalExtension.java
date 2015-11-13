package net.smartcosmos.pojo.extension;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.extension.ExtensionType;
import net.smartcosmos.model.extension.IExternalExtension;
import net.smartcosmos.pojo.base.NamedObject;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExternalExtension extends NamedObject<IExternalExtension> implements IExternalExtension
{
    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Standard.class)
    @NotNull
    @Size(max = SUPPORT_EMAIL_MAX_LENGTH)
    protected String supportEmail;

    @JsonView(JsonGenerationView.Published.class)
    @Size(max = WEBSITE_URL_MAX_LENGTH)
    protected String webSiteUrl;

    @JsonView(JsonGenerationView.Full.class)
    @NotNull
    @Size(max = CLIENT_ID_MAX_LENGTH)
    protected String clientId;

    @JsonView(JsonGenerationView.Full.class)
    @NotNull
    @Size(max = CLIENT_SECRET_MAX_LENGTH)
    protected String clientSecret;

    @JsonView(JsonGenerationView.Full.class)
    @NotNull
    @Size(max = REDIRECT_URL_MAX_LENGTH)
    protected String redirectUrl;

    @JsonView(JsonGenerationView.Published.class)
    @NotNull
    @Size(max = APP_CATALOG_URL_MAX_LENGTH)
    protected String appCatalogUrl;

    @JsonView(JsonGenerationView.Standard.class)
    @Size(max = LONG_DESCRIPTION_MAX_LENGTH)
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

        ExternalExtension extension = (ExternalExtension) o;

        if (registrationTimestamp != extension.registrationTimestamp) return false;
        if (version != extension.version) return false;
        if (!account.equals(extension.account)) return false;
        if (appCatalogUrl != null ? !appCatalogUrl.equals(extension.appCatalogUrl) : extension.appCatalogUrl != null)
            return false;
        if (!clientId.equals(extension.clientId)) return false;
        if (!clientSecret.equals(extension.clientSecret)) return false;
        if (extensionType != extension.extensionType) return false;
        if (longDescription != null
                ? !longDescription.equals(extension.longDescription)
                : extension.longDescription != null)
        {
            return false;
        }

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
