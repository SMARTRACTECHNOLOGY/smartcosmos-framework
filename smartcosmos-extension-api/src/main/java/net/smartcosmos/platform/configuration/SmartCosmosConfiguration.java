/*
 * Copyright (C> 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.configuration;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import net.smartcosmos.platform.bundle.batch.BatchFactory;
import net.smartcosmos.platform.bundle.quartz.QuartzFactory;
import net.smartcosmos.platform.bundle.transformation.TransformationsFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmartCosmosConfiguration extends Configuration
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
    private Map<String, String> permissions = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> visitors = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> resourceRegistrarClasses = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> serviceParameters = Maps.newLinkedHashMap();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @Valid
    @NotNull
    private BatchFactory batchFactory = new BatchFactory();

    @Valid
    @NotNull
    private QuartzFactory quartzFactory = new QuartzFactory();

    @Valid
    @NotNull
    private TransformationsFactory transformationsFactory = new TransformationsFactory();

    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @Valid
    @NotNull
    private EndpointsFactory endpointsFactory = new EndpointsFactory();

    @NotNull
    private Map<String, String> serverExtensions = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> serverExtensionConfigurationPaths = Maps.newLinkedHashMap();

    @JsonProperty
    private boolean supportRealmCheck;

    @JsonProperty
    private boolean supportDynamicRegistration;

    @JsonProperty
    private boolean supportStatusCheck;

    @JsonProperty
    private boolean supportNotifications;

    @JsonProperty
    private boolean supportExtensions;

    @JsonProperty
    private boolean supportMultimediaFiles;

    @JsonProperty
    private boolean supportInteractionSessions;

    @JsonProperty
    private boolean supportUsers;

    @JsonProperty
    private boolean includeEmailVerificationTokenInRegistrationJSON;

    @NotNull
    private Map<String, String> transactionHandlerClasses = Maps.newLinkedHashMap();

    public Map<String, String> getTransactionHandlerClasses()
    {
        return transactionHandlerClasses;
    }


    @JsonProperty("endpoints")
    public EndpointsFactory getEndpointsFactory()
    {
        return endpointsFactory;
    }

    @JsonProperty("endpoints")
    public void setEndpointsFactory(EndpointsFactory endpointsFactory)
    {
        this.endpointsFactory = endpointsFactory;
    }


    public Map<String, String> getServerExtensionConfigurationPaths()
    {
        return serverExtensionConfigurationPaths;
    }

    public Map<String, String> getServerExtensions()
    {
        return serverExtensions;
    }

    @JsonProperty
    private ArrayList<String> libraryHierarchy;

    @JsonProperty
    private ArrayList<Boolean> libraryLinkFlags;

    public boolean supportUsers()
    {
        return supportUsers;
    }

    public void setSupportUsers(boolean flag)
    {
        this.supportUsers = flag;
    }

    public HttpClientConfiguration getHttpClientConfiguration()
    {
        return httpClient;
    }

    public boolean supportInteractionSessions()
    {
        return supportInteractionSessions;
    }

    public void setSupportInteractionSessions(boolean flag)
    {
        this.supportInteractionSessions = flag;
    }

    public boolean supportExtensions()
    {
        return supportExtensions;
    }

    public void setSupportExtensions(boolean flag)
    {
        this.supportExtensions = flag;
    }

    public boolean supportMultimediaFiles()
    {
        return supportMultimediaFiles;
    }

    public void setSupportMultimediaFiles(boolean flag)
    {
        this.supportMultimediaFiles = flag;
    }

    public boolean supportRealmCheck()
    {
        return supportRealmCheck;
    }

    public void setSupportRealmCheck(boolean flag)
    {
        this.supportRealmCheck = flag;
    }

    public boolean supportStatusCheck()
    {
        return supportStatusCheck;
    }

    public void setSupportStatusCheck(boolean flag)
    {
        this.supportStatusCheck = flag;
    }

    public boolean supportNotifications()
    {
        return supportNotifications;
    }

    public void setSupportNotifications(boolean flag)
    {
        this.supportNotifications = flag;
    }

    public boolean includeEmailVerificationTokenInRegistrationJSON()
    {
        return includeEmailVerificationTokenInRegistrationJSON;
    }

    public void setIncludeEmailVerificationTokenInRegistration(boolean flag)
    {
        this.includeEmailVerificationTokenInRegistrationJSON = flag;
    }

    public boolean supportDynamicRegistration()
    {
        return supportDynamicRegistration;
    }

    public void setSupportDynamicRegistration(boolean flag)
    {
        this.supportDynamicRegistration = flag;
    }

    public void setLibraryHierarchy(List<String> libraryHierarchy)
    {
        LibraryHierarchyFactory.setLibraryHierarchyList(libraryHierarchy);
    }

    public void setLibraryLinkFlags(List<Boolean> libraryLinkFlags)
    {
        LibraryHierarchyFactory.setLibraryLinkFlagsList(libraryLinkFlags);
    }


    @JsonProperty("batch")
    public BatchFactory getBatchFactory()
    {
        return batchFactory;
    }

    @JsonProperty("batch")
    public void setBatchFactory(BatchFactory batchFactory)
    {
        this.batchFactory = batchFactory;
    }

    //
    //
    // General Platform Definition
    //
    //
    public String getAdminEmailAddress()
    {
        return adminEmailAddress;
    }

    public Map<String, String> getServiceClasses()
    {
        return serviceClasses;
    }

    public Map<String, String> getPermissions()
    {
        return permissions;
    }

    public Map<String, String> getVisitors()
    {
        return visitors;
    }

    public Map<String, String> getResourceRegistrarClasses()
    {
        return resourceRegistrarClasses;
    }

    public String getAppInstanceName()
    {
        return appInstanceName;
    }

    public String getAppName()
    {
        return appName;
    }

    public String getServerRoot()
    {
        return serverRoot;
    }

    public DataSourceFactory getDataSourceFactory()
    {
        return dataSourceFactory;
    }

    public String getEnterpriseKey()
    {
        return enterpriseKey;
    }

    public String getEnterpriseKeySignature()
    {
        return enterpriseKeySignature;
    }

    public Map<String, String> getServiceParameters()
    {
        return serviceParameters;
    }

    public String getUrlPattern()
    {
        return urlPattern;
    }

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

    //
    //
    // Quartz Scheduling Capabilities
    //
    //
    @JsonProperty("quartz")
    public QuartzFactory getQuartzFactory()
    {
        return quartzFactory;
    }

    @JsonProperty("quartz")
    public void setQuartzFactory(QuartzFactory quartzFactory)
    {
        this.quartzFactory = quartzFactory;
    }

    @JsonProperty("transformations")
    public TransformationsFactory getTransformationsFactory()
    {
        return transformationsFactory;
    }

    @JsonProperty("transformations")
    public void setTransformationsFactory(TransformationsFactory transformationsFactory)
    {
        this.transformationsFactory = transformationsFactory;
    }
}

