package net.smartcosmos.pojo;

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

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.context.Metadata;
import net.smartcosmos.util.json.JsonGenerationView;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.mapper.BooleanMapper;
import net.smartcosmos.util.mapper.DateMapper;
import net.smartcosmos.util.mapper.DoubleMapper;
import net.smartcosmos.util.mapper.FloatMapper;
import net.smartcosmos.util.mapper.IntegerMapper;
import net.smartcosmos.util.mapper.JsonMapper;
import net.smartcosmos.util.mapper.LongMapper;
import net.smartcosmos.util.mapper.StringMapper;
import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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

//    @Test
//    public void testBinaryEncodeDecode() throws JSONException, IOException
//    {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("foo", "bar");
//        jsonObject.put("now", true);
//
//        JsonMapper mapper = new JsonMapper();
//        byte[] bytes = mapper.toBytes(jsonObject);
//
//        IMetadata metadata = new Metadata();
//        metadata.setDataType(MetadataDataType.JSONType);
//        metadata.setKey("foo");
//        metadata.setRawValue(bytes);
//        metadata.setEntityReferenceType(EntityReferenceType.Device);
//        metadata.setReferenceUrn(UUID.randomUUID().toString());
//
//        String json = JsonUtil.toJson(metadata, JsonGenerationView.Minimum.class);
//        System.out.println(json);
//
//        IMetadata reconstitute = JsonUtil.fromJson(json, Metadata.class);
//        byte[] rawBytes = reconstitute.getRawValue();
//
//        JsonMapper mapper1 = new JsonMapper();
//        JSONObject reconstructedJson = mapper1.fromBytes(rawBytes);
//
//        Assert.assertEquals(jsonObject.getString("foo"), reconstructedJson.getString("foo"));
//
//    }

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
