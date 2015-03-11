package net.smartcosmos.objects.pojo.reporting;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.util.json.JsonGenerationView;

public class ObjectReport
{
    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = ObjectImpl.class)
    private IObject object;

    @JsonView(JsonGenerationView.Minimum.class)
    private Statistics statistics;

    public IObject getObject()
    {
        return object;
    }

    public void setObject(IObject object)
    {
        this.object = object;
    }

    public Statistics getStatistics()
    {
        return statistics;
    }

    public void setStatistics(Statistics statistics)
    {
        this.statistics = statistics;
    }
}
