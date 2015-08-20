/*
 * Copyright (C> 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.api.service;

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


import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.api.batch.IBatchUploadService;
import net.smartcosmos.platform.api.ext.IExtendable;
import net.smartcosmos.platform.api.visitor.IVisitor;
import org.quartz.Scheduler;

import java.util.SortedSet;

public interface IServiceFactory extends IService, IExtendable
{
    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String DIRECTORY_SERVICE = "directoryService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String NOTIFICATION_SERVICE = "notificationService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String EMAIL_SERVICE = "emailService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String DOWNLOAD_SERVICE = "downloadService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String STORAGE_SERVICE = "storageService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String QUEUE_SERVICE = "queueService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String EXCEPTION_SERVICE = "exceptionService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String TEMPLATE_SERVICE = "templateService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String EVENT_BROADCAST_NOTIFICATION_SERVICE = "eventBroadcastNotificationService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String EVENT_SERVICE = "eventService";

    /**
     * Case-sensitive name of the service's implementation class in the platform service's YML file.
     */
    String VISITOR_SERVICE = "visitorService";

    //
    // Pluggable Platform Specific Services
    //

    IDownloadService getDownloadService();

    IDirectoryService getDirectoryService();

    IStorageService getStorageService();

    INotificationService getNotificationService();

    IEmailService getEmailService();

    ITemplateService getTemplateService();

    IExceptionService getExceptionService();

    IEventBroadcastNotificationService getEventBroadcastNotificationService();

    IQueueService getQueueService();

    IBatchUploadService getUploadService();

    //
    // Event service feeds for notifications and full audit, including reads, of domain resources
    //

    IEventService getEventService(IAccount account);

    IEventService getEventService(IUser user);

    /**
     * Visitors.
     *
     * @param entityReferenceType type of visitor collections to retrieve
     * @return Sorted set of visitors arranged by their self-declared priorities
     */
    SortedSet<IVisitor> getVisitorsForType(EntityReferenceType entityReferenceType);

    /**
     * Obtains the default Quartz scheduler.
     *
     * @return Default Quartz scheduler
     */
    Scheduler getQuartzScheduler();
}
