/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.smartcosmos.model.base;

/**
 * Internal interface not suitable for public use. Captures the unique key assigned by the underlying persistence
 * engine, and a few helper functions.
 *
 * @param <T> Domain object type from the object model, e.g. <code>IObject</code>
 */
public interface IDomainResource<T> extends IUrnNamespace, IMoniker
{
    long getUniqueId();

    void setUniqueId(long uniqueId);

    void copy(T object);
}
