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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import org.hibernate.validator.constraints.NotEmpty;

import net.smartcosmos.platform.bundle.batch.BatchFactory;
import net.smartcosmos.platform.bundle.quartz.QuartzFactory;
import net.smartcosmos.platform.bundle.transformation.TransformationsFactory;

public class SmartCosmosConfiguration extends Configuration
{
    @NotEmpty
    @JsonProperty
    private String serverRoot;

    @NotEmpty
    @JsonProperty
    private String urlPattern;

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
    private Map<String, String> endpointMethodControl = Maps.newLinkedHashMap();

    private Map<String, String> visitors = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> resourceRegistrarClasses = Maps.newLinkedHashMap();

    @NotNull
    private Map<String, String> serviceParameters = Maps.newLinkedHashMap();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty
    private boolean migrateSchemaOnStartup;

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

    @Valid
    @NotNull
    LicenseFactory licenseFactory = new LicenseFactory();

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

    @JsonProperty
    private List<Map<String, Boolean>> libraryHierarchy = new ArrayList<>();

    //
    //
    // General Platform Definition
    //
    //
    public String getAdminEmailAddress()
    {
        return adminEmailAddress;
    }

    public String getAppInstanceName()
    {
        return appInstanceName;
    }

    public String getAppName()
    {
        return appName;
    }

    @JsonProperty("batch")
    public BatchFactory getBatchFactory()
    {
        return batchFactory;
    }

    public DataSourceFactory getDataSourceFactory()
    {
        return dataSourceFactory;
    }

    public Map<String, String> getEndpointMethodControl()
    {
        return endpointMethodControl;
    }

    @JsonProperty("endpoints")
    public EndpointsFactory getEndpointsFactory()
    {
        return endpointsFactory;
    }

    public HttpClientConfiguration getHttpClientConfiguration()
    {
        return httpClient;
    }

    @JsonProperty("license")
    public LicenseFactory getLicenseFactory()
    {
        return licenseFactory;
    }

    @JsonProperty("oauth2")
    public OAuth2Factory getOAuth2Factory()
    {
        return oAuth2Factory;
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

    public Map<String, String> getResourceRegistrarClasses()
    {
        return resourceRegistrarClasses;
    }

    public Map<String, String> getServerExtensionConfigurationPaths()
    {
        return serverExtensionConfigurationPaths;
    }

    public Map<String, String> getServerExtensions()
    {
        return serverExtensions;
    }

    public String getServerRoot()
    {
        return serverRoot;
    }

    public Map<String, String> getServiceClasses()
    {
        return serviceClasses;
    }

    public Map<String, String> getServiceParameters()
    {
        return serviceParameters;
    }

    public Map<String, String> getTransactionHandlerClasses()
    {
        return transactionHandlerClasses;
    }

    @JsonProperty("transformations")
    public TransformationsFactory getTransformationsFactory()
    {
        return transformationsFactory;
    }

    public String getUrlPattern()
    {
        return urlPattern;
    }

    public Map<String, String> getVisitors()
    {
        return visitors;
    }

    public boolean includeEmailVerificationTokenInRegistrationJSON()
    {
        return includeEmailVerificationTokenInRegistrationJSON;
    }

    public boolean isMigrateSchemaOnStartup()
    {
        return migrateSchemaOnStartup;
    }

    @JsonProperty("batch")
    public void setBatchFactory(final BatchFactory batchFactory)
    {
        this.batchFactory = batchFactory;
    }

    @JsonProperty("endpoints")
    public void setEndpointsFactory(final EndpointsFactory endpointsFactory)
    {
        this.endpointsFactory = endpointsFactory;
    }

    public void setIncludeEmailVerificationTokenInRegistration(final boolean flag)
    {
        this.includeEmailVerificationTokenInRegistrationJSON = flag;
    }

    public void setLibraryHierarchy(final List<Map<String, Boolean>> libraryHierarchy)
    {
        this.libraryHierarchy = libraryHierarchy;
        // spectacularly ugly, but the only way to read in ordered maps from YML -
        // they come in as an ordered array of single-entry maps.
        LinkedHashMap<String, Boolean> flattenedMap = new LinkedHashMap<>();
        for (Map<String, Boolean> arrayEntry: libraryHierarchy)
        {
            for (Map.Entry<String, Boolean> entry : arrayEntry.entrySet())
            {
                flattenedMap.put(entry.getKey(), entry.getValue());
            }
        }
        LibraryHierarchyFactory.setLibraryHierarchy(flattenedMap);
    }

    @JsonProperty("license")
    public void setLicenseFactory(final LicenseFactory licenseFactory)
    {
        this.licenseFactory = licenseFactory;
    }

    public void setMigrateSchemaOnStartup(final boolean migrateSchemaOnStartup)
    {
        this.migrateSchemaOnStartup = migrateSchemaOnStartup;
    }

    @JsonProperty("oauth2")
    public void setOAuth2Factory(final OAuth2Factory oAuth2Factory)
    {
        this.oAuth2Factory = oAuth2Factory;
    }

    @JsonProperty("quartz")
    public void setQuartzFactory(final QuartzFactory quartzFactory)
    {
        this.quartzFactory = quartzFactory;
    }

    public void setSupportDynamicRegistration(final boolean flag)
    {
        this.supportDynamicRegistration = flag;
    }

    public void setSupportExtensions(final boolean flag)
    {
        this.supportExtensions = flag;
    }

    public void setSupportInteractionSessions(final boolean flag)
    {
        this.supportInteractionSessions = flag;
    }

    public void setSupportMultimediaFiles(final boolean flag)
    {
        this.supportMultimediaFiles = flag;
    }

    public void setSupportNotifications(final boolean flag)
    {
        this.supportNotifications = flag;
    }

    public void setSupportRealmCheck(final boolean flag)
    {
        this.supportRealmCheck = flag;
    }

    public void setSupportStatusCheck(final boolean flag)
    {
        this.supportStatusCheck = flag;
    }

    public void setSupportUsers(final boolean flag)
    {
        this.supportUsers = flag;
    }

    @JsonProperty("transformations")
    public void setTransformationsFactory(final TransformationsFactory transformationsFactory)
    {
        this.transformationsFactory = transformationsFactory;
    }

    public boolean supportDynamicRegistration()
    {
        return supportDynamicRegistration;
    }

    public boolean supportExtensions()
    {
        return supportExtensions;
    }

    public boolean supportInteractionSessions()
    {
        return supportInteractionSessions;
    }

    public boolean supportMultimediaFiles()
    {
        return supportMultimediaFiles;
    }

    public boolean supportNotifications()
    {
        return supportNotifications;
    }

    public boolean supportRealmCheck()
    {
        return supportRealmCheck;
    }

    public boolean supportStatusCheck()
    {
        return supportStatusCheck;
    }

    public boolean supportUsers()
    {
        return supportUsers;
    }
}