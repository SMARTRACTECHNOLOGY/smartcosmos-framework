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

import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractBlockingQueueService<T> extends AbstractService implements Managed
{
    private static final int DEFAULT_THREAD_POOL_SIZE = 3;

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBlockingQueueService.class);

    private final String threadPoolSizeKey;

    private List<AbstractInterruptableRunner> activeThreads = new ArrayList<>();

    protected BlockingQueue<T> blockingQueue;

    protected AbstractBlockingQueueService(String serviceId, String name, String threadPoolSizeKey)
    {
        super(serviceId, name);
        this.threadPoolSizeKey = threadPoolSizeKey;
    }

    @Override
    public void initialize()
    {
        blockingQueue = new LinkedBlockingQueue<>();

        int fixedThreadPoolSize = DEFAULT_THREAD_POOL_SIZE;
        if (context.getConfiguration().getServiceParameters().containsKey(threadPoolSizeKey))
        {
            try
            {
                fixedThreadPoolSize = Integer.parseInt(context.getConfiguration()
                        .getServiceParameters()
                        .get(threadPoolSizeKey));

            } catch (NumberFormatException e)
            {
                LOG.warn("Cannot parse {} in YML file: {}", new Object[]{
                        threadPoolSizeKey,
                        context.getConfiguration().getServiceParameters().get(threadPoolSizeKey)});
            }
        } else
        {
            LOG.warn("YML does not contain a service parameter key named {}; default thread pool size being used",
                    threadPoolSizeKey);
        }

        for (int i = 0; i < fixedThreadPoolSize; i++)
        {
            AbstractInterruptableRunner runner = createInterruptableRunner();
            runner.setName("Service Thread (" + getName() + ") #" + (i + 1));
            activeThreads.add(runner);
            runner.start();

            LOG.info("Spawned Service Thread (" + getName() + ") #" + (i + 1));
        }
    }

    protected abstract AbstractInterruptableRunner createInterruptableRunner();

    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {
        //
        // Ensure we properly shutdown our threads
        for (AbstractInterruptableRunner thread : activeThreads)
        {
            thread.setTerminateFlag();
            thread.interrupt();
        }
    }
}

