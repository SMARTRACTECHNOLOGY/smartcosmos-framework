/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.geo;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import net.smartcosmos.util.json.JsonGenerationView;

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

    public GeometricShape setType(GeometricType type)
    {
        this.type = type;
        return this;
    }

    public JsonNode getCoordinates()
    {
        return coordinates;
    }

    public GeometricShape setCoordinates(JsonNode coordinates)
    {
        this.coordinates = coordinates;
        return this;
    }

    public List<GeometricShape> getGeometries()
    {
        return geometries;
    }

    public GeometricShape setGeometries(List<GeometricShape> geometries)
    {
        this.geometries = geometries;
        return this;
    }

    public float[] getBbox()
    {
        return bbox;
    }

    public GeometricShape setBbox(float[] bbox)
    {
        this.bbox = bbox;
        return this;
    }

    public GeometricShape getGeometry()
    {
        return geometry;
    }

    public GeometricShape setGeometry(GeometricShape geometry)
    {
        this.geometry = geometry;
        return this;
    }

    public Crs getCrs()
    {
        return crs;
    }

    public GeometricShape setCrs(Crs crs)
    {
        this.crs = crs;
        return this;
    }

    public List<GeometricShape> getFeatures()
    {
        return features;
    }

    public GeometricShape setFeatures(List<GeometricShape> features)
    {
        this.features = features;
        return this;
    }

    public JsonNode getProperties()
    {
        return properties;
    }

    public GeometricShape setProperties(JsonNode properties)
    {
        this.properties = properties;
        return this;
    }
}
