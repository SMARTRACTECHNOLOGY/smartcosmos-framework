package com.snapbundle.pojo.base;

public enum ErrorType
{
    ERR_UNAUTHORIZED(-1, "Authenticated user is not authorized to make this call"),
    ERR_UNLICENSED_FEATURE(-1, "Unlicensed feature"),
    ERR_EXTENSION_SECURITY_RESTRICTION(-2, "Extensions are not permitted to perform %s"),
    ERR_UNKNOWN_DEVICE_IDENTIFICATION(-3, "No device found with identification %s"),
    ERR_UNKNOWN_URN(-4, "No %s is assigned the URN %s"),
    ERR_MISSING_FIELD(-5, "JSON is missing a required field: %s");

    private final String errMsg;
    private final int errCode;

    ErrorType(int errCode, String errMsg)
    {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static ResponseObject createResponseObject(ErrorType errorType, String... substitutions)
    {
        return new ResponseObject(errorType.errCode, String.format(errorType.errMsg, substitutions));
    }

    public String getErrorMessage()
    {
        return errMsg;
    }

    public int getErrorCode()
    {
        return errCode;
    }
}
