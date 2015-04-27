package net.smartcosmos.platform.api.ext;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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
import net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration;
import net.smartcosmos.platform.configuration.SmartCosmosConfiguration;
import net.smartcosmos.platform.resource.IResourceRegistrar;

import java.util.List;

/**
 * Mandatory interface required by all SMART COSMOS server extensions.
 *
 * @param <T> Extension-specific configuration
 */
public interface ISmartCosmosExtension<T extends AbstractSmartCosmosExtensionConfiguration>
        extends ConfiguredBundle<SmartCosmosConfiguration>, IResourceRegistrar, Managed
{
    String getExtensionId();

    String getName();

    T getExtensionConfiguration();

    void setServerExtensionConfigurationPath(String extensionConfigurationPath);

    List<Class<?>> getEntities();
}
