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
import net.smartcosmos.platform.api.ext.IServerExtension;
import net.smartcosmos.platform.api.service.IServiceFactory;
import net.smartcosmos.platform.configuration.SmartCosmosConfiguration;
import org.apache.http.client.HttpClient;
import org.hibernate.SessionFactory;
import org.quartz.JobListener;
import org.quartz.Scheduler;

/**
 * Provides access to the server configuration, execution environment, including the entire set of platform services,
 * and to all dynamically registered SMART COSMOS extensions.
 *
 * @see IDAOFactory
 * @see IServiceFactory
 * @see IServerExtension
 */
public interface IContext extends JobListener
{
    /**
     * Configuration values defined in the server's YML file.
     *
     * @return server configuration
     */
    SmartCosmosConfiguration getConfiguration();

    /**
     * Data access factory.
     *
     * @return DAO factory
     */
    IDAOFactory getDAOFactory();

    /**
     * Service factory.
     *
     * @return service factory
     */
    IServiceFactory getServiceFactory();

    /**
     * Hibernate session factory.
     *
     * @return Hibernate session
     */
    SessionFactory getSessionFactory();

    /**
     * Dropwizard runtime environment.
     *
     * @return runtime environment
     */
    Environment getEnvironment();

    /**
     * Quartz Scheduler.
     *
     * @return Quartz scheduler
     */
    Scheduler getScheduler();

    /**
     * Apache HTTP client for making external web service calls.
     *
     * @return Apache HTTP client
     */
    HttpClient getHttpClient();

    /**
     * Existence check for a SMART COSMOS extension. Developer must have <i>a priori</i> knowledge of the
     * <code>extensionId</code>.
     *
     * @param extensionId extension ID to check for existence
     * @return true, if the extension ID is registered, false otherwise
     */
    boolean hasExtensions(String extensionId);

    /**
     * Retrieve the SMART COSMOS Extension linked to the specified <code>extensionId</code>, if it is exists.
     *
     * @param extensionId extension ID to check for existence
     * @return SMART COSMOS Extension associated with the specified <code>extensionId</code>, or null otherwise
     */
    IServerExtension lookupExtension(String extensionId);

    /**
     * Convenience method for determining if the runtime environment is a <b>Developer Edition</b> or a production
     * deployment.
     *
     * @return true, if the server is operating as a <b>Developer Edition</b>
     */
    boolean isDeveloperEdition();
}
