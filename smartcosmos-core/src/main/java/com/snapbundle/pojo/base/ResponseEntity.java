package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.util.JsonGenerationView;
import com.snapbundle.util.JsonUtil;

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
