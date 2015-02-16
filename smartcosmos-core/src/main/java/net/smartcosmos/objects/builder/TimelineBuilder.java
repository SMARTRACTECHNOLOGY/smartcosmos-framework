package net.smartcosmos.objects.builder;

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

import net.smartcosmos.builder.AbstractReferentialBuilder;
import net.smartcosmos.objects.model.context.ITimelineEntry;
import net.smartcosmos.objects.pojo.context.TimelineEntry;

public final class TimelineBuilder extends AbstractReferentialBuilder<ITimelineEntry, TimelineBuilder>
{
    public TimelineBuilder()
    {
        super(new TimelineEntry());
        instance.setActive(true);
    }

    public TimelineBuilder setName(String name)
    {
        instance.setName(name);
        return this;
    }

    public TimelineBuilder setDescription(String description)
    {
        instance.setDescription(description);
        return this;
    }

    public TimelineBuilder setActive(boolean flag)
    {
        instance.setActive(flag);
        return this;
    }

    public TimelineBuilder setRecordedTimestamp(long recordedTimestamp)
    {
        instance.setRecordedTimestamp(recordedTimestamp);
        return this;
    }

    public TimelineBuilder setVisible(boolean flag)
    {
        instance.setVisible(flag);
        return this;
    }

    public TimelineBuilder setHighlight(boolean flag)
    {
        instance.setHighlight(flag);
        return this;
    }
}
