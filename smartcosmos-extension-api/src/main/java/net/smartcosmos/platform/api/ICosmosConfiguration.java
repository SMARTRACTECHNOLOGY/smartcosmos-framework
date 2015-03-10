package net.smartcosmos.platform.api;

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

import io.dropwizard.db.DataSourceFactory;
import net.smartcosmos.platform.authentication.OAuth2Factory;

import java.util.Map;

/**
 * Base configuration for every SMART COSMOS platform service.
 */
public interface ICosmosConfiguration
{
    DataSourceFactory getDataSourceFactory();

    //
    // Service Classes
    //
    Map<String, String> getServiceClasses();

    Map<String, String> getServiceParameters();

    //
    // Resource Registrars
    //
    Map<String, String> getResourceRegistrarClasses();

    String getAppName();

    String getAppInstanceName();

    String getUrlPattern();

    String getEnterpriseKeySignature();

    String getEnterpriseKey();

    //
    // Email From Address used by IEmailService
    //

    String getAdminEmailAddress();

    //
    // HTTP Header Paging Metadata
    //

    String getServerRoot();

    //
    // OAuth 2.0 Functionality
    //
    OAuth2Factory getOAuth2Factory();
}
