package net.smartcosmos.platform.api.service;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.IService;
import org.quartz.Scheduler;

public interface ICosmosServiceFactory<T extends ICosmosContext> extends IService<T>
{
    String DIRECTORY_SERVICE = "directoryService";

    String NOTIFICATION_SERVICE = "notificationService";

    String EMAIL_SERVICE = "emailService";

    String DOWNLOAD_SERVICE = "downloadService";

    String STORAGE_SERVICE = "storageService";

    String QUEUE_SERVICE = "queueService";

    String EXCEPTION_SERVICE = "exceptionService";

    String TEMPLATE_SERVICE = "templateService";

    String EVENT_BROADCAST_NOTIFICATION_SERVICE = "eventBroadcastNotificationService";

    String EVENT_SERVICE = "eventService";

    //
    // Pluggable Platform Specific Services
    //

    IDownloadService<T> getDownloadService();

    IDirectoryService getDirectoryService();

    IStorageService<T> getStorageService();

    INotificationService<T> getNotificationService();

    IEmailService<T> getEmailService();

    ITemplateService<T> getTemplateService();

    IExceptionService<T> getExceptionService();

    IEventBroadcastNotificationService<T> getEventBroadcastNotificationService();

    IQueueService<T> getQueueService();

    //
    // Event service feeds for notifications and full audit, including reads, of domain resources
    //

    IEventService<T> getEventService(IAccount account);

    IEventService<T> getEventService(IUser user);

    /**
     * Obtains the default Quartz scheduler.
     *
     * @return Default Quartz scheduler
     */
    Scheduler getQuartzScheduler();
}
