package net.smartcosmos.platform.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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

import io.dropwizard.lifecycle.Managed;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.platform.pojo.service.RecordableEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractMultiThreadedEventService extends AbstractService implements IEventService, Managed
{
    private static final int DEFAULT_THREAD_POOL_SIZE = 3;

    public static final String EVENT_THREAD_POOL_SIZE = "eventThreadPoolSize";

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMultiThreadedEventService.class);

    private List<AbstractEventRunner> activeThreads = new ArrayList<>();

    protected BlockingQueue<RecordableEvent> blockingQueue;

    protected AbstractMultiThreadedEventService(String serviceId, String name)
    {
        super(serviceId, name);
    }

    @Override
    public void initialize()
    {
        blockingQueue = new LinkedBlockingQueue<>();

        int fixedThreadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        if (context.getConfiguration().getServiceParameters().containsKey(EVENT_THREAD_POOL_SIZE))
        {
            try
            {
                fixedThreadPoolSize = Integer.parseInt(context.getConfiguration()
                        .getServiceParameters()
                        .get(EVENT_THREAD_POOL_SIZE));

            } catch (NumberFormatException e)
            {
                LOG.warn("Cannot parse eventThreadPoolSize in YML file: {}",
                        context.getConfiguration().getServiceParameters().get(EVENT_THREAD_POOL_SIZE));
            }
        }

        for (int i = 0; i < fixedThreadPoolSize; i++)
        {
            AbstractEventRunner runner = createEventRunner();
            runner.setName("Persisted Event Service - Thread " + (i + 1));
            activeThreads.add(runner);
            runner.start();

            LOG.info("Spawned Event Service Thread #" + (i + 1));
        }
    }

    protected abstract AbstractEventRunner createEventRunner();

    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {
        //
        // Ensure we properly shutdown our threads
        for (AbstractEventRunner runner : activeThreads)
        {
            runner.setTerminateFlag();
            runner.interrupt();
        }
    }
}
