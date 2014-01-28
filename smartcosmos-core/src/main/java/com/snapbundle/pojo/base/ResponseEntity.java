/*
 * SnapBundle SDK
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

package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.util.json.JsonGenerationView;
import com.snapbundle.util.json.JsonUtil;

public class ResponseEntity
{
    @JsonView(JsonGenerationView.Minimum.class)
    private int code;

    @JsonView(JsonGenerationView.Minimum.class)
    private String message;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Result getResult()
    {
        return Result.translate(getCode());
    }

    public static String toJson(Result result)
    {
        return ResponseEntity.toJson(result, null);
    }

    public static String toJson(Result result, Object... args)
    {
        ResponseEntity re = new ResponseEntity();
        re.setMessage(String.format(result.getFormattedMessage(), args));
        re.setCode(result.getCode());

        return JsonUtil.toJson(re);
    }
}
