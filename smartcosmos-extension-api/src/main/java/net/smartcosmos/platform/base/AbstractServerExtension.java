package net.smartcosmos.platform.base;

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

import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.configuration.DefaultConfigurationFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.ext.IServerExtension;
import net.smartcosmos.platform.configuration.SmartCosmosConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public abstract class AbstractServerExtension<T extends AbstractSmartCosmosExtensionConfiguration>
        implements IServerExtension<T>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractServerExtension.class);

    protected final String extensionId;

    protected final String name;

    private String extensionConfigurationPath;

    protected T extensionConfiguration;

    private final Class<T> extensionConfigurationClass;

    protected SmartCosmosConfiguration smartCosmosConfiguration;

    protected IContext context;

    protected AbstractServerExtension(String extensionId, String name, Class<T> extensionConfigurationClass)
    {
        this.extensionId = extensionId;
        this.name = name;
        this.extensionConfigurationClass = extensionConfigurationClass;
    }

    @Override
    public String getExtensionId()
    {
        return extensionId;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public T getExtensionConfiguration()
    {
        return extensionConfiguration;
    }

    protected String getServerExtensionConfigurationPath()
    {
        return extensionConfigurationPath;
    }

    @Override
    public void setServerExtensionConfigurationPath(String extensionConfigurationPath)
    {
        this.extensionConfigurationPath = extensionConfigurationPath;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap)
    {
        ConfigurationFactory<T> cf = new DefaultConfigurationFactoryFactory<T>()
                .create(extensionConfigurationClass,
                        bootstrap.getValidatorFactory().getValidator(),
                        bootstrap.getObjectMapper(),
                        "dw");

        try
        {
            extensionConfiguration = (T) cf.build(bootstrap.getConfigurationSourceProvider(),
                    getServerExtensionConfigurationPath());

            initialize(extensionConfiguration);

        } catch (Exception e)
        {
            handleInitializationException(e);
        }
    }

    protected void initialize(T extensionConfiguration) throws Exception
    {

    }

    protected void handleInitializationException(Exception e)
    {
        LOG.error(e.toString() + "\n" + e.getMessage() + " " + e.getCause());
    }

    @Override
    public void run(SmartCosmosConfiguration configuration, Environment environment) throws Exception
    {
        this.smartCosmosConfiguration = configuration;
    }

    @Override
    public void registerResources(IContext context)
    {
        this.context = context;
    }

    @Override
    public List<Class<?>> getEntities()
    {
        return Collections.emptyList();
    }

    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {

    }


}
