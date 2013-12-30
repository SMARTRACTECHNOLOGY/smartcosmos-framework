package com.snapbundle.geo;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.util.json.JsonGenerationView;

public class Crs
{
    @JsonView(JsonGenerationView.Minimum.class)
    private CrsType type;

    @JsonView(JsonGenerationView.Minimum.class)
    private CrsProperties properties;

    public CrsType getType()
    {
        return type;
    }

    public void setType(CrsType type)
    {
        this.type = type;
    }

    public CrsProperties getProperties()
    {
        return properties;
    }

    public void setProperties(CrsProperties properties)
    {
        this.properties = properties;
    }
}
