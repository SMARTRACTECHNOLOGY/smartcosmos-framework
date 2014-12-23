

package net.smartcosmos.objects.builder;

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
