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

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import net.smartcosmos.platform.api.IObjectsContext;
import net.smartcosmos.platform.api.ext.IObjectsServerExtension;
import net.smartcosmos.platform.api.service.IObjectsServiceFactory;
import net.smartcosmos.platform.authentication.DelegatingAuthProvider;
import net.smartcosmos.platform.authentication.PlatformBasicAuthenticator;
import net.smartcosmos.platform.authentication.PlatformOAuthAuthenticator;
import net.smartcosmos.platform.configuration.ObjectsConfiguration;
import net.smartcosmos.platform.resource.IResourceRegistrar;
import net.smartcosmos.platform.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractCosmosApplication extends Application<ObjectsConfiguration>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCosmosApplication.class);

    public static final int PLATFORM_RELEASE_REVISION = 2;

    protected IObjectsServiceFactory serviceFactory;

    protected final List<IObjectsServerExtension> extensions = new ArrayList<>();

    protected abstract void extendedInitialization(IObjectsContext context);

    protected abstract IObjectsServiceFactory createServiceFactory();

    protected abstract IObjectsContext createContext(ObjectsConfiguration configuration, Environment environment);

    protected abstract void defineHealthChecks(IObjectsContext context);

    @Override
    public void run(ObjectsConfiguration configuration, Environment environment)
    {
        try
        {
            serviceFactory = createServiceFactory();
            LOG.info("Launching platform with a service factory of {}", serviceFactory.getClass().getName());

            preContextCreation(configuration, environment);

            LOG.info("Creating platform context...");
            IObjectsContext context = createContext(configuration, environment);

            serviceFactory.setContext(context);
            serviceFactory.initialize();

            LOG.info("Registering endpoint resources...");
            defineResources(context);

            LOG.info("Registering health checks...");
            defineHealthChecks(context);

            LOG.info("Binding platform auth provider...");
            registerAuthProvider(context);

            postContextCreation(context);

            LOG.info("Processing extended platform initialization...");
            extendedInitialization(context);

            LOG.info("{} is open for business", configuration.getAppInstanceName());
        } catch (Exception ex)
        {
            LOG.error(ex.getMessage());
            LOG.error("Exception during bootstrap. Platform is shutting down...");
            throw new RuntimeException(ex);
        }
    }

    protected void preContextCreation(ObjectsConfiguration configuration, Environment environment)
    {

    }

    protected void registerAuthProvider(IObjectsContext context)
    {
        //
        // AuthN and AuthZ
        //
        context.getEnvironment().jersey().register(new DelegatingAuthProvider<>(
                new PlatformBasicAuthenticator(context),
                new PlatformOAuthAuthenticator(context),
                context.getConfiguration().getAppName()));
    }

    protected void postContextCreation(IObjectsContext context)
    {

    }

    @SuppressWarnings("unchecked")
    protected void defineResources(IObjectsContext context)
    {
        Set<Map.Entry<String, String>> entrySet = context.getConfiguration().getResourceRegistrarClasses().entrySet();

        for (Map.Entry<String, String> entry : entrySet)
        {
            LOG.info("Defining resources from registrar " + entry.getKey());

            IResourceRegistrar registrar = ClassUtil.create(IResourceRegistrar.class, entry.getValue());
            registrar.registerResources(context);
        }
    }
}
