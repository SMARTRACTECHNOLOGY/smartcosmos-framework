package net.smartcosmos.platform.bundle.quartz;

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

import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzManager implements Managed
{
    private static final Logger LOG = LoggerFactory.getLogger(QuartzManager.class);

    private final Scheduler scheduler;

    public QuartzManager(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }

    @Override
    public void start() throws Exception
    {
        LOG.info("Starting Quartz scheduler...");
        scheduler.start();
    }

    @Override
    public void stop() throws Exception
    {
        LOG.info("Shutting down Quartz scheduler (waiting for running jobs to complete)...");
        scheduler.shutdown(true);
    }
}
