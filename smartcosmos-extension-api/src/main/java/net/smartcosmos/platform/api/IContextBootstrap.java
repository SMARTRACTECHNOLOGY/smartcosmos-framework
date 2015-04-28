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
import org.quartz.Scheduler;

import java.util.List;

public interface IContextBootstrap extends IContext
{
    void setEnvironment(Environment environment);

    void setConfiguration(SmartCosmosConfiguration configuration);

    void setDAOFactory(IDAOFactory daoFactory);

    void setServiceFactory(IServiceFactory serviceFactory);

    void setScheduler(Scheduler scheduler);

    void setHttpClient(HttpClient httpClient);

    void setExtensions(List<ISmartCosmosExtension> extensions);
}
