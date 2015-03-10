package net.smartcosmos.platform.base;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import net.smartcosmos.platform.api.ICosmosConfiguration;
import net.smartcosmos.platform.authentication.OAuth2Factory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public abstract class AbstractCosmosConfiguration extends Configuration implements ICosmosConfiguration
{
    @NotEmpty
    @JsonProperty
    private String serverRoot;

    @NotEmpty
    @JsonProperty
    private String urlPattern;

    @JsonProperty
    private String enterpriseKey;

    @JsonProperty
    private String enterpriseKeySignature;

    @NotEmpty
    @JsonProperty
    private String adminEmailAddress;

    @NotEmpty
    @JsonProperty
    private String appInstanceName;

    @NotEmpty
    @JsonProperty
    private String appName;

    @Valid
    @NotNull
    private OAuth2Factory oAuth2Factory = new OAuth2Factory();

    @NotNull
    private Map<String, String> serviceClasses = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> resourceRegistrarClasses = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> serviceParameters = Maps.newLinkedHashMap();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    //
    //
    // General Platform Definition
    //
    //
    @Override
    public String getAdminEmailAddress()
    {
        return adminEmailAddress;
    }

    @Override
    public Map<String, String> getServiceClasses()
    {
        return serviceClasses;
    }

    @Override
    public Map<String, String> getResourceRegistrarClasses()
    {
        return resourceRegistrarClasses;
    }

    @Override
    public String getAppInstanceName()
    {
        return appInstanceName;
    }

    @Override
    public String getAppName()
    {
        return appName;
    }

    @Override
    public String getServerRoot()
    {
        return serverRoot;
    }

    @Override
    public DataSourceFactory getDataSourceFactory()
    {
        return dataSourceFactory;
    }

    @Override
    public String getEnterpriseKey()
    {
        return enterpriseKey;
    }

    @Override
    public String getEnterpriseKeySignature()
    {
        return enterpriseKeySignature;
    }

    @Override
    public Map<String, String> getServiceParameters()
    {
        return serviceParameters;
    }

    @Override
    public String getUrlPattern()
    {
        return urlPattern;
    }

    @Override
    @JsonProperty("oauth2")
    public OAuth2Factory getOAuth2Factory()
    {
        return oAuth2Factory;
    }

    @JsonProperty("oauth2")
    public void setOAuth2Factory(OAuth2Factory oAuth2Factory)
    {
        this.oAuth2Factory = oAuth2Factory;
    }
}
