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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Thread pool factory for {@link QuartzBundle}s. For complete details on the configuration properties and
 * acceptable values, please refer to the
 * <a href="http://www.quartz-scheduler.org/generated/2.2.1/pdf/Quartz_Scheduler_Configuration_Guide.pdf">Quartz
 * Scheduler Configuration Guide, Version 2.2.1</a>.
 * <p>
 * <p>
 * <b>Configuration Parameters:</b>
 * <table>
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Quartz Property Mapping</td>
 * </tr>
 * <tr>
 * <td>{@code threadPoolClass}</td>
 * <td>org.quartz.simpl.SimpleThreadPool</td>
 * <td>org.quartz.threadPool.class</td>
 * </tr>
 * <tr>
 * <td>{@code threadCount}</td>
 * <td>3</td>
 * <td>org.quartz.threadPool.threadCount</td>
 * </tr>
 * <tr>
 * <td>{@code threadPriority}</td>
 * <td>5 (NORMAL)</td>
 * <td>org.quartz.threadPool.threadPriority</td>
 * </tr>
 * <tr>
 * <td>{@code threadNamePrefix}</td>
 * <td>null</td>
 * <td>org.quartz.threadPool.threadNamePrefix</td>
 * </tr>
 * <tr>
 * <td>{@code makeThreadsDaemons}</td>
 * <td>false</td>
 * <td>org.quartz.threadPool.makeThreadsDaemons</td>
 * </tr>
 * </table>
 */
public class QuartzThreadPool
{
    @NotNull
    //Maps to org.quartz.threadPool.class
    private String threadPoolClass = "org.quartz.simpl.SimpleThreadPool";

    @Min(1)
    @Max(100)
    //Maps to org.quartz.threadPool.threadCount
    private Integer threadCount = 3;

    @Min(1)
    @Max(10)
    //Maps to org.quartz.threadPool.threadPriority
    private Integer threadPriority = 5;

    //Maps to org.quartz.threadPool.threadNamePrefix
    private String threadNamePrefix = null;

    //Maps to org.quartz.threadPool.makeThreadsDaemons
    private Boolean makeThreadsDaemons = false;

    public String getThreadPoolClass()
    {
        return threadPoolClass;
    }

    public void setThreadPoolClass(String threadPoolClass)
    {
        this.threadPoolClass = threadPoolClass;
    }

    public Integer getThreadCount()
    {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount)
    {
        this.threadCount = threadCount;
    }

    public Integer getThreadPriority()
    {
        return threadPriority;
    }

    public void setThreadPriority(Integer threadPriority)
    {
        this.threadPriority = threadPriority;
    }

    public String getThreadNamePrefix()
    {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix)
    {
        this.threadNamePrefix = threadNamePrefix;
    }

    public Boolean getMakeThreadsDaemons()
    {
        return makeThreadsDaemons;
    }

    public void setMakeThreadsDaemons(Boolean makeThreadsDaemons)
    {
        this.makeThreadsDaemons = makeThreadsDaemons;
    }
}
