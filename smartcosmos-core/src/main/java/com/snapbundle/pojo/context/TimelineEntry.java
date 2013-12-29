package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.model.context.ITimelineEntry;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.json.JsonGenerationView;

public class TimelineEntry extends ReferentialObject<ITimelineEntry> implements ITimelineEntry
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String name;

    @JsonView(JsonGenerationView.Standard.class)
    protected String description;

    @JsonView(JsonGenerationView.Minimum.class)
    protected boolean highlightFlag;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean visibleFlag;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean activeFlag;

    @JsonView(JsonGenerationView.Standard.class)
    protected double lat;

    @JsonView(JsonGenerationView.Standard.class)
    protected double lon;

    @JsonView(JsonGenerationView.Standard.class)
    protected double alt;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean hasGeoLocation = false;

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
    public boolean hasGeoLocation()
    {
        return hasGeoLocation;
    }

    @Override
    public void setLat(double lat)
    {
        this.hasGeoLocation = true;
        this.lat = lat;
    }

    @Override
    public void setLon(double lon)
    {
        this.hasGeoLocation = true;
        this.lon = lon;
    }

    @Override
    public void setAlt(double alt)
    {
        this.hasGeoLocation = true;
        this.alt = alt;
    }

    @Override
    public double getLat()
    {
        return lat;
    }

    @Override
    public double getLon()
    {
        return lon;
    }

    @Override
    public double getAlt()
    {
        return alt;
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
    public long getTimestamp()
    {
        return timelineTimestamp;
    }

    @Override
    public void setTimestamp(long timestamp)
    {
        this.timelineTimestamp = timestamp;
    }


    @Override
    public void copy(ITimelineEntry object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
