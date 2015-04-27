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

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuartzListJobsTask extends Task
{
    private final SchedulerFactory schedulerFactory;

    private final ObjectMapper objectMapper;

    public QuartzListJobsTask(SchedulerFactory schedulerFactory, ObjectMapper objectMapper)
    {
        super("quartz-jobs");
        this.schedulerFactory = schedulerFactory;
        this.objectMapper = objectMapper;
    }

    @Timed
    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception
    {
        int ttlGroupCount = 0;

        Map<String, Object> groupJobsMap = new HashMap<>();
        List<Object> groups = new ArrayList<>();

        List<String> targetGroupNames = new ArrayList<>();

        if (parameters == null || !parameters.containsKey("groupNames"))
        {
            targetGroupNames.addAll(schedulerFactory.getScheduler().getJobGroupNames());
        } else
        {
            ImmutableCollection<String> groupNames = parameters.get("groupNames");
            String[] names = groupNames.iterator().next().split(",");
            targetGroupNames.addAll(Arrays.asList(names));
        }

        for (String groupName : targetGroupNames)
        {
            ttlGroupCount++;

            Map<String, Object> currentGroupMap = new HashMap<>();
            currentGroupMap.put("name", groupName);

            List<Object> currentGroupJobs = generateJobsForGroup(groupName);
            currentGroupMap.put("groupJobCount", currentGroupJobs.size());
            currentGroupMap.put("jobs", currentGroupJobs);

            groups.add(currentGroupMap);
        }

        groupJobsMap.put("groups", groups);
        groupJobsMap.put("ttlGroups", ttlGroupCount);

        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupJobsMap);
        output.println(jsonString);
        output.flush();
    }

    private List<Object> generateJobsForGroup(String groupName) throws SchedulerException
    {
        Set<JobKey> jobKeys = schedulerFactory.getScheduler().getJobKeys(GroupMatcher.<JobKey>groupEquals(groupName));
        List<Object> currentGroupJobs = new ArrayList<>();

        // enumerate each job in group
        for (JobKey jobKey : jobKeys)
        {
            Map<String, Object> currentJobMap = new HashMap<>();
            currentJobMap.put("job", jobKey.getName());
            currentGroupJobs.add(currentJobMap);
        }

        return currentGroupJobs;
    }
}
