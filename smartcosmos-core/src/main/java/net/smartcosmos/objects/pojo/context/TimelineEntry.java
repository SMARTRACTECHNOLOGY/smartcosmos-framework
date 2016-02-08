package net.smartcosmos.objects.pojo.context;

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
import net.smartcosmos.objects.model.context.ITimelineEntry;
import net.smartcosmos.pojo.base.ReferentialObject;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TimelineEntry extends ReferentialObject<ITimelineEntry> implements ITimelineEntry
{
    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    @Size(max = NAME_MAX_LENGTH)
    protected String name;

    @JsonView(JsonGenerationView.Standard.class)
    protected String description;

    @JsonView(JsonGenerationView.Minimum.class)
    protected boolean highlightFlag;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean visibleFlag;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean activeFlag;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long timelineTimestamp;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public boolean isHighlight()
    {
        return highlightFlag;
    }

    @Override
    public void setHighlight(boolean highlight)
    {
        this.highlightFlag = highlight;
    }

    @Override
    public boolean isVisible()
    {
        return visibleFlag;
    }

    @Override
    public void setVisible(boolean visible)
    {
        this.visibleFlag = visible;
    }

    @Override
    public long getRecordedTimestamp()
    {
        return timelineTimestamp;
    }

    @Override
    public void setRecordedTimestamp(long recordedTimestamp)
    {
        this.timelineTimestamp = recordedTimestamp;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TimelineEntry that = (TimelineEntry) o;

        if (activeFlag != that.activeFlag) return false;
        if (highlightFlag != that.highlightFlag) return false;
        if (timelineTimestamp != that.timelineTimestamp) return false;
        if (visibleFlag != that.visibleFlag) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (highlightFlag ? 1 : 0);
        result = 31 * result + (visibleFlag ? 1 : 0);
        result = 31 * result + (activeFlag ? 1 : 0);
        result = 31 * result + (int) (timelineTimestamp ^ (timelineTimestamp >>> 32));
        return result;
    }
}
