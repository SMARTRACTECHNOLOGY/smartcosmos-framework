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

import net.smartcosmos.platform.base.AbstractService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.newJob;

/**
 * Base class for all SMART COSMOS Quartz job definitions that provides automatic {@link org.quartz.JobDetail}
 * creation with an identity of {@link #getName()} and {@link #getGroup()}. The {@link #schedule()} uses the
 * {@link #createJob()} and the {@link #createTrigger()} callback.
 *
 * @param <T> {@link org.quartz.Job} class type
 */
public abstract class AbstractQuartzJobDefinition<T extends Job>
        extends AbstractService implements IQuartzJobDefinition
{
    private final String group;

    private Class<T> classInstance;

    public AbstractQuartzJobDefinition(String serviceId, String name, String group, Class<T> classInstance)
    {
        super(serviceId, name);
        this.group = group;
        this.classInstance = classInstance;
    }

    @Override
    public String getGroup()
    {
        return group;
    }

    @Override
    public Class<T> getJobClass()
    {
        return classInstance;
    }

    protected abstract Trigger createTrigger();

    protected JobDetail createJob()
    {
        return newJob(getJobClass())
                .withIdentity(getName(), getGroup())
                .build();
    }

    @Override
    public void schedule() throws SchedulerException
    {
        context
                .getServiceFactory()
                .getQuartzScheduler()
                .scheduleJob(createJob(), createTrigger());
    }
}
