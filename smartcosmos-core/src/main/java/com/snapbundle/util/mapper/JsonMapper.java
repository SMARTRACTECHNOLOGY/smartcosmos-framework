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

package com.snapbundle.util.mapper;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonMapper implements IMetadataValueMapper<JSONObject>
{
    private final StringMapper stringMapper = new StringMapper();

    @Override
    public byte[] toBytes(JSONObject value)
    {
        byte[] result = new byte[0];

        try
        {
            result = stringMapper.toBytes(value.toString(3));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JSONObject fromBytes(byte[] rawValue)
    {
        JSONObject jsonObject = new JSONObject();

        String json = stringMapper.fromBytes(rawValue);
        try
        {
            jsonObject = new JSONObject(json);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;
    }
}