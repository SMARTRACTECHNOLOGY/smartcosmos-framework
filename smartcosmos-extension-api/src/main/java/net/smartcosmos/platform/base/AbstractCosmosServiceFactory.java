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

package net.smartcosmos.platform.base;

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

import com.google.common.base.Preconditions;
import io.dropwizard.lifecycle.Managed;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.api.service.IDirectoryService;
import net.smartcosmos.platform.api.service.IDownloadService;
import net.smartcosmos.platform.api.service.IEmailService;
import net.smartcosmos.platform.api.service.IEventBroadcastNotificationService;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.platform.api.service.IExceptionService;
import net.smartcosmos.platform.api.service.INotificationService;
import net.smartcosmos.platform.api.service.ICosmosServiceFactory;
import net.smartcosmos.platform.api.service.IQueueService;
import net.smartcosmos.platform.api.service.IStorageService;
import net.smartcosmos.platform.api.service.ITemplateService;
import net.smartcosmos.platform.util.ClassUtil;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCosmosServiceFactory<T extends ICosmosContext>
        extends AbstractService<T> implements ICosmosServiceFactory<T>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCosmosServiceFactory.class);

    private IDirectoryService<T> directoryService;

    private INotificationService<T> notificationService;

    private IEmailService<T> emailService;

    private IDownloadService<T> downloadService;

    private IStorageService<T> storageService;

    private IExceptionService<T> exceptionService;

    private ITemplateService<T> templateService;

    private IQueueService<T> queueService;

    private IEventBroadcastNotificationService<T> eventBroadcastNotificationService;

    private IEventService<T> eventService;

    protected AbstractCosmosServiceFactory(String serviceId, String name)
    {
        super(serviceId, name);
    }

    private void doInjections(IService<T> service)
    {
        service.setContext(context);
        service.initialize();

        if (Managed.class.isAssignableFrom(service.getClass()))
        {
            LOG.info("Registering service class {} as managed object...", service.getName());
            context.getEnvironment().lifecycle().manage((Managed) service);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize()
    {
        String directoryServiceClass = context.getConfiguration().getServiceClasses().get(DIRECTORY_SERVICE);
        LOG.info("Initializing Directory Services class ({})", directoryServiceClass);
        directoryService = ClassUtil.create(IDirectoryService.class, directoryServiceClass);
        doInjections(directoryService);

        String notificationServiceClazz = context.getConfiguration().getServiceClasses().get(NOTIFICATION_SERVICE);
        LOG.info("Initializing Notification Services class ({})", notificationServiceClazz);
        notificationService = ClassUtil.create(INotificationService.class, notificationServiceClazz);
        doInjections(notificationService);

        String queueServiceClazz = context.getConfiguration().getServiceClasses().get(QUEUE_SERVICE);
        LOG.info("Initializing Queue Services class ({})", queueServiceClazz);
        queueService = ClassUtil.create(IQueueService.class, queueServiceClazz);
        doInjections(queueService);

        String emailServiceClazz = context.getConfiguration().getServiceClasses().get(EMAIL_SERVICE);
        LOG.info("Initializing Email Services class ({})", emailServiceClazz);
        emailService = ClassUtil.create(IEmailService.class, emailServiceClazz);
        doInjections(emailService);

        String downloadServiceClazz = context.getConfiguration().getServiceClasses().get(DOWNLOAD_SERVICE);
        LOG.info("Initializing Download Services class ({})", downloadServiceClazz);
        downloadService = ClassUtil.create(IDownloadService.class, downloadServiceClazz);
        doInjections(downloadService);

        String storageServiceClazz = context.getConfiguration().getServiceClasses().get(STORAGE_SERVICE);
        LOG.info("Initializing Storage Services class ({})", storageServiceClazz);
        storageService = ClassUtil.create(IStorageService.class, storageServiceClazz);
        doInjections(storageService);

        String exceptionServiceClazz = context.getConfiguration().getServiceClasses().get(EXCEPTION_SERVICE);
        LOG.info("Initializing Exception Services class ({})", exceptionServiceClazz);
        exceptionService = ClassUtil.create(IExceptionService.class, exceptionServiceClazz);
        doInjections(exceptionService);

        String templateServiceClazz = context.getConfiguration().getServiceClasses().get(TEMPLATE_SERVICE);
        LOG.info("Initializing Template Services class ({})", templateServiceClazz);
        templateService = ClassUtil.create(ITemplateService.class, templateServiceClazz);
        doInjections(templateService);

        String eventBroadcastNotificationServiceClazz = context.getConfiguration()
                .getServiceClasses()
                .get(EVENT_BROADCAST_NOTIFICATION_SERVICE);
        LOG.info("Initializing Event Broadcast Notification Services class ({})",
                eventBroadcastNotificationServiceClazz);
        eventBroadcastNotificationService = ClassUtil.create(IEventBroadcastNotificationService.class,
                eventBroadcastNotificationServiceClazz);
        doInjections(eventBroadcastNotificationService);

        String eventServiceClazz = context.getConfiguration().getServiceClasses().get(EVENT_SERVICE);
        LOG.info("Initializing Event Services class ({})", eventServiceClazz);
        eventService = ClassUtil.create(IEventService.class, eventServiceClazz);
        doInjections(eventService);
    }

    @Override
    public IEventService<T> getEventService(IUser user)
    {
        Preconditions.checkNotNull(user, "user cannot be null");
        return getEventService(user.getAccount());
    }

    @Override
    public IEventService<T> getEventService(IAccount account)
    {
        return eventService;
    }

    @Override
    public IEventBroadcastNotificationService<T> getEventBroadcastNotificationService()
    {
        return eventBroadcastNotificationService;
    }

    @Override
    public IExceptionService<T> getExceptionService()
    {
        return exceptionService;
    }

    @Override
    public INotificationService<T> getNotificationService()
    {
        return notificationService;
    }

    @Override
    public IDirectoryService<T> getDirectoryService()
    {
        return directoryService;
    }

    @Override
    public IEmailService<T> getEmailService()
    {
        return emailService;
    }

    @Override
    public IDownloadService<T> getDownloadService()
    {
        return downloadService;
    }

    @Override
    public ITemplateService<T> getTemplateService()
    {
        return templateService;
    }

    @Override
    public IStorageService<T> getStorageService()
    {
        return storageService;
    }

    @Override
    public IQueueService<T> getQueueService()
    {
        return queueService;
    }

    @Override
    public Scheduler getQuartzScheduler()
    {
        return context.getScheduler();
    }
}

