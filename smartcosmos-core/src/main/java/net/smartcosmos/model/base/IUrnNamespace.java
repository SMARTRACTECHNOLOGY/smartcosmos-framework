package net.smartcosmos.model.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

import java.util.UUID;

/**
 *
 * Sole means of uniquely identifying a specific object in the SMART COSMOS platform.
 *
 * */
public interface IUrnNamespace
{
    /**
     * System assigned, globally unique key used to reference a specific record. When
     * defining a referential object, this URN maps to the
     * {@link IReferentialObject#getReferenceUrn()} value.
     *
     * @return System-assigned urn
     * @see IReferentialObject
     */
    String getUrn();

    void setUrn(String urn);

    UUID getSystemUuid();

    /**
     * System managed field for tracking the last time the record was updated. This field
     * is predominately used to create a valid ETag for client-side cache management.
     *
     * @return Last modified time (based on the Unix timestamp)
     */
    long getLastModifiedTimestamp();
}
