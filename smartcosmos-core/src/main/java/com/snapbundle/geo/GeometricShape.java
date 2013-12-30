package com.snapbundle.geo;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.snapbundle.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.List;

public class GeometricShape
{
    @JsonView(JsonGenerationView.Minimum.class)
    private Crs crs;

    @JsonView(JsonGenerationView.Minimum.class)
    private GeometricType type;

    @JsonView(JsonGenerationView.Minimum.class)
    private JsonNode coordinates;

    @JsonView(JsonGenerationView.Minimum.class)
    private List<GeometricShape> geometries = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private JsonNode properties;

    @JsonView(JsonGenerationView.Minimum.class)
    private List<GeometricShape> features = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private float[] bbox;

    @JsonView(JsonGenerationView.Minimum.class)
    private GeometricShape geometry;

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

    public List<GeometricShape> getGeometries()
    {
        return geometries;
    }

    public void setGeometries(List<GeometricShape> geometries)
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

    public GeometricShape getGeometry()
    {
        return geometry;
    }

    public void setGeometry(GeometricShape geometry)
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

    public List<GeometricShape> getFeatures()
    {
        return features;
    }

    public void setFeatures(List<GeometricShape> features)
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
