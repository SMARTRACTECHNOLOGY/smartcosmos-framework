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
import net.smartcosmos.platform.bundle.batch.BatchFactory;
import net.smartcosmos.platform.bundle.quartz.QuartzFactory;
import net.smartcosmos.platform.bundle.transformation.TransformationsFactory;

import java.util.List;
import java.util.Map;

public interface SmartCosmosConfiguration
{

    //
    //
    // General Platform Definition
    //
    //
    String getAdminEmailAddress();

    String getAppInstanceName();

    String getAppName();

    @JsonProperty("batch")
    BatchFactory getBatchFactory();

    Map<String, String> getEndpointMethodControl();

    @JsonProperty("endpoints")
    EndpointsFactory getEndpointsFactory();

    @JsonProperty("license")
    LicenseFactory getLicenseFactory();

    @JsonProperty("oauth2")
    OAuth2Factory getOAuth2Factory();

    //
    //
    // Quartz Scheduling Capabilities
    //
    //
    @JsonProperty("quartz")
    QuartzFactory getQuartzFactory();

    Map<String, String> getResourceRegistrarClasses();

    Map<String, String> getServerExtensionConfigurationPaths();

    Map<String, String> getServerExtensions();

    String getServerRoot();

    Map<String, String> getServiceClasses();

    Map<String, String> getServiceParameters();

    Map<String, String> getTransactionHandlerClasses();

    @JsonProperty("transformations")
    TransformationsFactory getTransformationsFactory();

    String getUrlPattern();

    Map<String, String> getVisitors();

    boolean includeEmailVerificationTokenInRegistrationJSON();

    boolean isMigrateSchemaOnStartup();

    @JsonProperty("batch")
    void setBatchFactory(final BatchFactory batchFactory);

    @JsonProperty("endpoints")
    void setEndpointsFactory(final EndpointsFactory endpointsFactory);

    void setIncludeEmailVerificationTokenInRegistration(final boolean flag);

    void setLibraryHierarchy(final List<String> libraryHierarchy);

    void setLibraryLinkFlags(final List<Boolean> libraryLinkFlags);

    @JsonProperty("license")
    void setLicenseFactory(final LicenseFactory licenseFactory);

    void setMigrateSchemaOnStartup(final boolean migrateSchemaOnStartup);

    @JsonProperty("oauth2")
    void setOAuth2Factory(final OAuth2Factory oAuth2Factory);

    @JsonProperty("quartz")
    void setQuartzFactory(final QuartzFactory quartzFactory);

    void setSupportDynamicRegistration(final boolean flag);

    void setSupportExtensions(final boolean flag);

    void setSupportInteractionSessions(final boolean flag);

    void setSupportMultimediaFiles(final boolean flag);

    void setSupportNotifications(final boolean flag);

    void setSupportRealmCheck(final boolean flag);

    void setSupportStatusCheck(final boolean flag);

    void setSupportUsers(final boolean flag);

    @JsonProperty("transformations")
    void setTransformationsFactory(final TransformationsFactory transformationsFactory);

    boolean supportDynamicRegistration();

    boolean supportExtensions();

    boolean supportInteractionSessions();

    boolean supportMultimediaFiles();

    boolean supportNotifications();

    boolean supportRealmCheck();

    boolean supportStatusCheck();

    boolean supportUsers();
}