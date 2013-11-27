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

package com.snapbundle.pojo.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class ResponseObject
{
    private static final ObjectMapper mapper = new ObjectMapper();

    private long result;

    private String message;

    public static ResponseObject fromJson(String json) throws IOException
    {
        return mapper.readValue(json, ResponseObject.class);
    }

    public ResponseObject(long result, String message)
    {
        this.result = result;
        this.message = message;
    }

    public ResponseObject()
    {

    }

    public ResponseObject(long result)
    {
        this.result = result;
    }

    public String toJson() throws JsonProcessingException
    {
        return mapper.writeValueAsString(this);
    }

    public long getResult()
    {
        return result;
    }

    public String getMessage()
    {
        return message;
    }
}

