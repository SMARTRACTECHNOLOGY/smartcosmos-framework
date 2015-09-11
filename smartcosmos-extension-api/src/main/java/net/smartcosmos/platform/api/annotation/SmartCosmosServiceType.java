/*
 *  SMART COSMOS Marketplace
 *
 *  Copyright (c) 2015, Smartrac Technology Fletcher, Inc.
 *  267 Cane Creek Rd, Fletcher, NC, 28732, USA
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of
 *  Smartrac Technology Fletcher, Inc. ("Confidential Information").
 *  You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement
 *  you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.api.annotation;

/**
 * Service extension type.
 * <p>
 * The constants of this enumerated type describe the type of service extension being implemented. They are used
 * in conjunction with the {@link SmartCosmosServiceExtension} meta-annotation type to specify the type of service
 * extension.
 * </p>
 */
public enum SmartCosmosServiceType
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
     * Remote storage service, e.g. AWS S3, Box.com, DropBox, or any other type of storage system
     */
    STORAGE
}
