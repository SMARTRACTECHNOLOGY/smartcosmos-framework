package com.snapbundle.geo;

public class Crs
{
    private CrsType type;

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
