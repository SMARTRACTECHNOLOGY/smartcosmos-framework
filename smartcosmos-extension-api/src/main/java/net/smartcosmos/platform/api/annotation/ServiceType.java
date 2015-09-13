/*
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
 */

package net.smartcosmos.platform.api.annotation;

/**
 * Service extension type.
 * <p>
 * The constants of this enumerated type describe the type of service extension being implemented. They are used
 * in conjunction with the {@link ServiceExtension} meta-annotation type to specify the type of service
 * extension.
 * </p>
 */
public enum ServiceType
{
    /**
     * Manage account registrations, defines new users, and allows an administrator to define a specific password or
     * trigger a password reset process via email.
     */
    DIRECTORY,
    /**
     * Provides HTTP download services for remote content, eliminating an extra POST to a platform service by merely
     * pointing to a URL instead of having to actually stream over a (potentially) slow connection the actual content.
     */
    DOWNLOAD,
    /**
     * Delivers email messages in dual format (plain text and HTML).
     */
    EMAIL,
    /**
     * Service that captures all reads and writes to any and all platform service endpoints.
     */
    EVENT,
    /**
     * Service that broadcasts all reads and writes to any and all platform service endpoints to
     * a 3rd party external system.
     */
    EVENT_BROADCAST_NOTIFICATION,
    /**
     * Service that forwards notifications to topics and pub0sub endpoints.
     */
    NOTIFICATION,
    /**
     * Queue service.
     */
    QUEUE,
    /**
     * Remote storage service, e.g. AWS S3, Box.com, DropBox, or any other type of storage system.
     */
    STORAGE
}
