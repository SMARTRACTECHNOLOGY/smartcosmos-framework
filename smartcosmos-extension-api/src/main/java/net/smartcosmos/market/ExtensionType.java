package net.smartcosmos.market;

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

import net.smartcosmos.platform.api.ext.IServerExtension;

public enum ExtensionType
{
    /**
     * Server-side SMART COSMOS extension bundle.
     *
     * @see IServerExtension
     */
    ServerExtension,

    /**
     * External OAuth 2 authorized extension.
     *
     * @see net.smartcosmos.model.extension.IExtension
     */
    ExternalExtension,

    /**
     * Core SMART COSMOS service.
     *
     * @see net.smartcosmos.platform.api.IService
     * @see net.smartcosmos.platform.api.service.IDirectoryService
     * @see net.smartcosmos.platform.api.service.IEventService
     * @see net.smartcosmos.platform.api.service.IStorageService
     * @see net.smartcosmos.platform.api.service.IEventBroadcastNotificationService
     * @see net.smartcosmos.platform.api.service.INotificationService
     * @see net.smartcosmos.platform.api.service.IDownloadService
     * @see net.smartcosmos.platform.api.service.IEmailService
     * @see net.smartcosmos.platform.api.service.IQueueService
     * @see net.smartcosmos.platform.api.service.ITemplateService
     * @see net.smartcosmos.platform.api.service.IExceptionService
     * @see net.smartcosmos.platform.api.batch.IBatchUploadService
     */
    ServiceExtension,

    /**
     * Quartz Job.
     *
     * @see net.smartcosmos.platform.bundle.quartz.IQuartzJobDefinition
     */
    QuartzJob,

    /**
     * Transaction Handler.
     *
     * @see net.smartcosmos.platform.api.transaction.ITransactionHandler
     */
    TransactionHandler
}
