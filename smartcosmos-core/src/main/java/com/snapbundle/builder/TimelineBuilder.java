/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.builder;

import com.snapbundle.model.context.ITimelineEntry;
import com.snapbundle.pojo.context.TimelineEntry;

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
