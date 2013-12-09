package com.snapbundle.geo;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class GeometricObject
{
    private Crs crs;

    private GeometricType type;

    private JsonNode coordinates;

    private List<GeometricObject> geometries = new ArrayList<>();

    private JsonNode properties;

    private List<GeometricObject> features = new ArrayList<>();

    private float[] bbox;

    private GeometricObject geometry;

    public GeometricType getType()
    {
        return type;
    }

    public void setType(GeometricType type)
    {
        this.type = type;
    }

    public JsonNode getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(JsonNode coordinates)
    {
        this.coordinates = coordinates;
    }

    public List<GeometricObject> getGeometries()
    {
        return geometries;
    }

    public void setGeometries(List<GeometricObject> geometries)
    {
        this.geometries = geometries;
    }

    public float[] getBbox()
    {
        return bbox;
    }

    public void setBbox(float[] bbox)
    {
        this.bbox = bbox;
    }

    public GeometricObject getGeometry()
    {
        return geometry;
    }

    public void setGeometry(GeometricObject geometry)
    {
        this.geometry = geometry;
    }

    public Crs getCrs()
    {
        return crs;
    }

    public void setCrs(Crs crs)
    {
        this.crs = crs;
    }

    public List<GeometricObject> getFeatures()
    {
        return features;
    }

    public void setFeatures(List<GeometricObject> features)
    {
        this.features = features;
    }

    public JsonNode getProperties()
    {
        return properties;
    }

    public void setProperties(JsonNode properties)
    {
        this.properties = properties;
    }
}
