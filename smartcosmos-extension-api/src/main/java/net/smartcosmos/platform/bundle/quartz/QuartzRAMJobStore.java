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

/**
 * Thread pool factory for {@link QuartzBundle}s. For complete details on the configuration properties and
 * acceptable values, please refer to the
 * <a href="http://www.quartz-scheduler.org/generated/2.2.1/pdf/Quartz_Scheduler_Configuration_Guide.pdf">Quartz
 * Scheduler Configuration Guide, Version 2.2.1</a>.
 * <p/>
 * <p/>
 * <b>Configuration Parameters:</b>
 * <table>
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Quartz Property Mapping</td>
 * </tr>
 * <tr>
 * <td>{@code misfireThreshold}</td>
 * <td>60000</td>
 * <td>org.quartz.jobStore.misfireThreshold</td>
 * </tr>
 * </table>
 */
public class QuartzRAMJobStore
{
    //Maps to org.quartz.jobStore.class
    private static final String JOB_STORE_CLASS = "org.quartz.simpl.RAMJobStore";

    //Maps to org.quartz.jobStore.misfireThreshold
    private Integer misfireThreshold = 60000;

    public String getJobStoreClass()
    {
        return JOB_STORE_CLASS;
    }

    public Integer getMisfireThreshold()
    {
        return misfireThreshold;
    }

    public void setMisfireThreshold(Integer misfireThreshold)
    {
        this.misfireThreshold = misfireThreshold;
    }
}
