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

/**
 * Minimum definition of a service plugin, represented by a UUID service ID and a human-readable name. The
 * platform's context will be injected into the instance.
 * <p/>
 * Each platform service is accessed via the {@link net.smartcosmos.platform.api.service.IServiceFactory}.
 * The specific concrete implementation of the service is defined within the server's YML file.
 */
public interface IService
{
    /**
     * UUID that identifies this unique service.
     *
     * @return Service UUID
     */
    String getServiceId();

    /**
     * Human readable name of the given service.
     *
     * @return service name
     */
    String getName();

    /**
     * Injected runtime context which the service operates within.
     *
     * @param context runtime context
     */
    void setContext(IContext context);

    /**
     * Perform any initialization or configuration steps required before the service is made available.
     */
    void initialize();
}
