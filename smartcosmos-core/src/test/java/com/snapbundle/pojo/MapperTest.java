/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IMetadata;
import com.snapbundle.model.context.MetadataDataType;
import com.snapbundle.pojo.context.Metadata;
import com.snapbundle.util.JsonGenerationView;
import com.snapbundle.util.mapper.BooleanMapper;
import com.snapbundle.util.mapper.DateMapper;
import com.snapbundle.util.mapper.DoubleMapper;
import com.snapbundle.util.mapper.FloatMapper;
import com.snapbundle.util.mapper.IntegerMapper;
import com.snapbundle.util.mapper.JsonMapper;
import com.snapbundle.util.mapper.LongMapper;
import com.snapbundle.util.mapper.StringMapper;
import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

public class MapperTest
{
    @Test
    public void testStringMapper()
    {
        String text = "the quick brown fox jumps over the lazy dog";

        StringMapper mapper = new StringMapper();
        byte[] bytes = mapper.toBytes(text);

        String result = mapper.fromBytes(bytes);
        Assert.assertEquals(text, result);
    }

    @Test
    public void testBooleanMapper()
    {
        boolean flag = true;

        BooleanMapper mapper = new BooleanMapper();
        byte[] bytes = mapper.toBytes(flag);
        boolean output = mapper.fromBytes(bytes);

        Assert.assertEquals(flag, output);
    }

    @Test
    public void testDateMapper()
    {
        Date now = new Date();

        DateMapper mapper = new DateMapper();
        byte[] bytes = mapper.toBytes(now);
        Date output = mapper.fromBytes(bytes);

        Assert.assertEquals(now, output);
    }

    @Test
    public void testFloatMapper()
    {
        float value = 1234.123f;

        FloatMapper mapper = new FloatMapper();
        byte[] bytes = mapper.toBytes(value);
        float output = mapper.fromBytes(bytes);

        Assert.assertEquals(value, output);
    }

    @Test
    public void testDoubleMapper()
    {
        double value = 1234.123d;

        DoubleMapper mapper = new DoubleMapper();
        byte[] bytes = mapper.toBytes(value);
        double output = mapper.fromBytes(bytes);

        Assert.assertEquals(value, output);
    }

    @Test
    public void testIntegerMapper()
    {
        int value = 12345;

        IntegerMapper mapper = new IntegerMapper();
        byte[] bytes = mapper.toBytes(value);
        int output = mapper.fromBytes(bytes);

        Assert.assertEquals(value, output);
    }

    @Test
    public void testLongMapper()
    {
        long value = 12345L;

        LongMapper mapper = new LongMapper();
        byte[] bytes = mapper.toBytes(value);
        long output = mapper.fromBytes(bytes);

        Assert.assertEquals(value, output);
    }

    @Test
    public void testBinaryEncodeDecode() throws JSONException, IOException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("foo", "bar");
        jsonObject.put("now", true);

        JsonMapper mapper = new JsonMapper();
        byte[] bytes = mapper.toBytes(jsonObject);

        IMetadata metadata = new Metadata();
        metadata.setDataType(MetadataDataType.JSONType);
        metadata.setKey("foo");
        metadata.setRawValue(bytes);
        metadata.setEntityReferenceType(EntityReferenceType.Device);
        metadata.setReferenceUrn("foo:bar");

        String json = metadata.toJson(JsonGenerationView.Minimum.class);
        System.out.println(json);

        IMetadata reconstitute = Metadata.fromJson(json);
        byte[] rawBytes = reconstitute.getRawValue();

        JsonMapper mapper1 = new JsonMapper();
        JSONObject reconstructedJson = mapper1.fromBytes(rawBytes);

        Assert.assertEquals(jsonObject.getString("foo"), reconstructedJson.getString("foo"));

    }

    @Test
    public void testJsonMapper() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("foo", "bar");
        jsonObject.put("now", true);

        JsonMapper mapper = new JsonMapper();
        byte[] bytes = mapper.toBytes(jsonObject);

        JSONObject reconstituted = mapper.fromBytes(bytes);

        Assert.assertEquals(jsonObject.get("foo"), reconstituted.get("foo"));
    }


}
