package net.smartcosmos.platform.api.ext;

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

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.lifecycle.Managed;
import net.smartcosmos.platform.base.AbstractServerExtension;
import net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration;
import net.smartcosmos.platform.configuration.SmartCosmosConfiguration;
import net.smartcosmos.platform.resource.IResourceRegistrar;

import java.util.List;

/**
 * Entry point interface required by all SMART COSMOS server extensions.
 *
 * @param <T> Extension-specific configuration
 * @see AbstractSmartCosmosExtensionConfiguration
 * @see AbstractServerExtension
 */
public interface IServerExtension<T extends AbstractSmartCosmosExtensionConfiguration>
        extends ConfiguredBundle<SmartCosmosConfiguration>, IResourceRegistrar, Managed
{
    /**
     * Every SMART COSMOS Extension must provide a UUID that unqiuely identifies this extension within the global
     * SMART COSMOS space.
     *
     * @return UUID uniquely assigned to this specific extension
     */
    String getExtensionId();

    /**
     * Logical, human readable name that describes the extension.
     *
     * @return Extension name
     */
    String getName();

    /**
     * Access to the type-safe extension specific configuration file separate from the standard
     * {@link SmartCosmosConfiguration} YML file.
     *
     * @return non-null, type-safe extension configuration
     */
    T getExtensionConfiguration();

    /**
     * Server extensions are defined in the server's main YML file in two parts. First, the YML defines the fully
     * qualified name of the class that implements this interface. Second, using the same key, the extension specific
     * YML configuration file path is defined. At bootstrap time, the path to the YML file is dynamically injected into
     * the base class {@link AbstractServerExtension} for proper processing and
     * loading.
     * <p/>
     * <b>NOTE:</b> Rarely should the developer ever have to do anything with this method when relying on the
     * base class.
     *
     * @param extensionConfigurationPath Path to the extension's specific YML file
     */
    void setServerExtensionConfigurationPath(String extensionConfigurationPath);

    /**
     * Collection of Hibernate annotated classes that should be dynamically added to the Dropwizard Hibernate bundle
     * for processing at startup time.
     *
     * @return Non-null, but possibly empty, list of Hibernate annotated classes to register during server bootstrap
     */
    List<Class<?>> getEntities();
}
