package net.smartcosmos.platform.bundle.quartz;

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

import net.smartcosmos.platform.api.IService;
import org.quartz.SchedulerException;

/**
 * Stand alone representation of a Quartz Job and Trigger that is to be scheduled
 * automatically at bootstrap.
 * <p/>
 * <B>NOTE:</B> Every concrete implementation must support a no-arg public constructor.
 *
 * @see {@link AbstractQuartzJobDefinition}
 */
public interface IQuartzJobDefinition extends IService
{
    /**
     * Key to access the SMART COSMOS context within the Quartz {@link org.quartz.JobExecutionContext}.
     */
    String SMART_COSMOS_CONTEXT = "net.smartcosmos.platform.api.Context";

    /**
     * Per the Quartz documentation, the {@link #getName()} and the group name represent the unique key
     * of the job within the given {@link org.quartz.Scheduler} instance.
     *
     * @return Group name
     */
    String getGroup();

    /**
     * Class that actually implements {@link org.quartz.Job} interface.
     *
     * @return Job class
     */
    Class<?> getJobClass();

    /**
     * Callback to actually schedule the job and trigger with the {@link org.quartz.Scheduler}.
     *
     * @throws SchedulerException
     * @see {@link AbstractQuartzJobDefinition}
     */
    void schedule() throws SchedulerException;
}
