package net.smartcosmos.platform.util;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.Field;
import net.smartcosmos.util.mapper.JsonMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RawValueTest
{
    @Test
    public void testExtraction() throws JSONException, IOException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Field.RAW_VALUE_FIELD, "ewogICAiZm9vIjogImJhciIsCiAgICJub3ciOiB0cnVlCn0=");

        RawValue rv = RawValue.fromJson(jsonObject);

        JsonMapper mapper = new JsonMapper();
        JSONObject reconstituted = mapper.fromBytes(rv.getRawValue());

        Assert.assertEquals(reconstituted.getString("foo"), "bar");
    }
}
