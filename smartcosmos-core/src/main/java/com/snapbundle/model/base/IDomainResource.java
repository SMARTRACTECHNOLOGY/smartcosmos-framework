/*
 * SnapBundle™ SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.model.base;

/**
 * Internal interface not suitable for public use. Captures the unique key assigned by the underlying persistence
 * engine, and a few helper functions.
 *
 * @param <T> Concrete domain object type from the object model.
 */
public interface IDomainResource<T> extends IUrnNamespace, IMoniker
{
    long getUniqueId();

    void setUniqueId(long uniqueId);

    long getLastModifiedTimestamp();

    void copy(T object);
}
