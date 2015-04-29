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

package net.smartcosmos.platform.api;

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

import io.dropwizard.setup.Environment;
import net.smartcosmos.platform.api.dao.IDAOFactory;
import net.smartcosmos.platform.api.ext.ISmartCosmosExtension;
import net.smartcosmos.platform.api.service.IServiceFactory;
import net.smartcosmos.platform.configuration.SmartCosmosConfiguration;
import org.apache.http.client.HttpClient;
import org.hibernate.SessionFactory;
import org.quartz.JobListener;
import org.quartz.Scheduler;

public interface IContext extends JobListener
{
    /**
     * Collection of platform-specific configuration values as defined in the platform's .yml file.
     *
     * @return Platform-specific configuration
     */
    SmartCosmosConfiguration getConfiguration();

    /**
     * Collection of platform-specific DAO factories.
     *
     * @return Platform-specific DAO factory
     */
    IDAOFactory getDAOFactory();

    /**
     * Collection of pluggable platform-specific services as defined in the platform's .yml file.
     *
     * @return Platform-specific pluggable service factory
     */
    IServiceFactory getServiceFactory();

    SessionFactory getSessionFactory();

    Environment getEnvironment();

    Scheduler getScheduler();

    HttpClient getHttpClient();

    boolean hasExtensions(String extensionId);

    ISmartCosmosExtension lookupExtension(String extensionId);

    boolean isDeveloperEdition();
}
