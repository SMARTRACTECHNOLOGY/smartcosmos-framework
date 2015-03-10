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

import io.dropwizard.setup.Environment;
import net.smartcosmos.platform.api.dao.ICosmosDAOFactory;
import net.smartcosmos.platform.api.service.ICosmosServiceFactory;
import org.apache.http.client.HttpClient;
import org.quartz.Scheduler;

public interface ICosmosContextBootstrap<S extends ICosmosConfiguration,
        T extends ICosmosDAOFactory,
        U extends ICosmosServiceFactory> extends ICosmosContext<S, T, U>
{
    void setEnvironment(Environment environment);

    void setConfiguration(S configuration);

    void setDAOFactory(T daoFactory);

    void setServiceFactory(U serviceFactory);

    void setScheduler(Scheduler scheduler);

    void setHttpClient(HttpClient httpClient);
}
