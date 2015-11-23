package net.smartcosmos.platform.api.ext;

import java.util.Set;

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
 * Identifies those platform services that support dynamic extension registration capabilities. For example, the
 * {@link net.smartcosmos.platform.api.dao.IDAOFactory} is "extendable" such that extension-specific DAOs can be
 * dynamically registered and accessed both within and throughout the executing server platform. As such, it is possible
 * to logically (not presently physically enforced) create a dependency between two extensions from 3rd parties.
 */
public interface IExtendable<T>
{
    /**
     * Determine if the named extension is registered.
     *
     * @param key
     *            well-known key used to lookup the extension
     * @return true, if the key was found
     */
    boolean has(String key);

    /**
     * Type-safe mechanism for accessing a registered extension.
     *
     * @param key
     *            well-known key used to lookup the extension
     * @param classType
     *            expected type the extension should be cast as
     * @param <E>
     *            type-safe return of the extension
     * @return instance of the extension, or null if no extension is registered under the specified key
     */
    <E> E lookup(String key, Class<E> classType);

    /**
     * The interface can also choose a generic interface or implementation that it expects all of the extendable class
     * to implement. This is great for generic CRUD operations, or common interfaces that are shared.
     * 
     * @param key
     *            well-known key used to lookup the extension
     * @return generic implementation of the interface, which might be extended with otherwise unknown functionality.
     */
    T lookup(String key);

    /**
     * Register an extension (instance) under the specified key. If an existing extension is already registered under
     * the assigned key, the existing one is replaced with this one.
     *
     * @param key
     *            well-known key that will be used to lookup the extension
     * @param instance
     *            instance of the extension to register
     */
    void register(String key, Object instance);

    /**
     * Register an extension (instance) under the specified key. If an existing extension is already registered under
     * the assigned key, the existing one is replaced with this one.
     *
     * @param key
     *            well-known key that will be used to lookup the extension
     * @param instance
     *            instance of the extension to register
     * @param briefDescription
     *            human readable description of the extension point
     */
    void register(String key, Object instance, String briefDescription);

    /**
     * 
     * @return all registers keys in to the extensible construct.
     */
    Set<String> keySet();
}
