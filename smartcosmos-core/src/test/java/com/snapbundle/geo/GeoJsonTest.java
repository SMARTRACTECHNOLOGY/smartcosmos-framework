/*
 * SnapBundle™ SDK
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

package com.snapbundle.geo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class GeoJsonTest
{
    private static ObjectMapper mapper = new ObjectMapper();

    static
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    @Test
    public void testNamedCrs() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "named-crs.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        CrsWrapper crs = mapper.readValue(json, CrsWrapper.class);
    }

    @Test
    public void testLinkedCrs() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "linked-crs.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        CrsWrapper crs = mapper.readValue(json, CrsWrapper.class);
    }

    @Test
    public void testPoint() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "point.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testLineString() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "line-string.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testPolygon() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "polygon.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testPolygonWithHoles() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "polygon-with-holes.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testMultilineString() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "multiline-string.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testMultipoint() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "multipoint.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testMultiPolygon() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "multi-polygon.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testGeometricCollection() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "geometric-collection.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testBbox() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "bbox.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testFeatureCollection() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "feature-collection.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);
    }

    @Test
    public void testFeatureCollectionBothWays() throws IOException
    {
        URL url = Resources.getResource(GeoJsonTest.class, "feature-collection.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        GeometricShape crs = mapper.readValue(json, GeometricShape.class);

        String jsonOutput = mapper.writeValueAsString(crs);

        GeometricShape newCrs = mapper.readValue(jsonOutput, GeometricShape.class);
    }

}
