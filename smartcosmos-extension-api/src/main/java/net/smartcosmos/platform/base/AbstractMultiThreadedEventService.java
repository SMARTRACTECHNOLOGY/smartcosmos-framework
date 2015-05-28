package net.smartcosmos.platform.base;

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

import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.platform.pojo.service.RecordableEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMultiThreadedEventService extends AbstractBlockingQueueService<RecordableEvent>
        implements IEventService
{
    public static final String EVENT_THREAD_POOL_KEY = "eventThreadPoolSize";

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMultiThreadedEventService.class);

    protected AbstractMultiThreadedEventService(String serviceId, String name)
    {
        super(serviceId, name, EVENT_THREAD_POOL_KEY);
    }
}
