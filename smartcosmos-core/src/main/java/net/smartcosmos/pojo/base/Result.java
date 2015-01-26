package net.smartcosmos.pojo.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

/**
 * Collection of standardized {@link ResponseEntity#getCode()} values returned from the SMART COSMOS Objects platform.
 * Each code is mapped to a substitution string used to create consistently worded error messages.
 * <p/>
 * Effectively, any {@link ResponseEntity#getCode()} value less than zero is an error condition.
 */
public enum Result
{
    OK(1, "%s"),
    ERR_FAILURE(0, "%s"),
    ERR_UNAUTHORIZED(-1, "Authenticated user is not authorized to make this call"),
    ERR_NO_SUCH_URN(-2, "No such URN"),
    ERR_UNLICENSED_FEATURE(-3, "Unlicensed feature"),
    ERR_UNKNOWN_ENTITY_TYPE(-4, "Unknown Entity Reference Type: %s"),
    ERR_MISSING_FIELD(-5, "JSON is missing a required field: %s"),
    ERR_UNKNOWN_DEVICE_IDENTIFICATION(-6, "No device found with identification %s"),
    ERR_ALREADY_EXISTS(-7, "A %s with matching key %s already exists"),
    ERR_NOT_EXISTS(-8, "No matching record with %s of %s exists"),
    ERR_UNKNOWN_ENTITY(-9, "Unknown %s entity with urn %s"),
    ERR_UNKNOWN_TYPE(-10, "%s enum does not define type %s"),
    ERR_SESSION_ALREADY_CLOSED(-11, "session with URN %s was previously closed"),
    ERR_NO_SUCH_EMAIL(-12, "No user associated with email address %s"),
    ERR_NO_FILE_CONTENT(-13, "File URN %s exists but is flagged as pending content upload"),
    ERR_MISSING_AUTHENTICATION_HEADER(-14, "Endpoint requires authentication"),

    ERR_EXTENSION_SECURITY_RESTRICTION(-50, "Extensions are not permitted to perform %s"),
    ERR_EXTENSION_NO_ACCESS(-51, "Caller lacked the authorization to complete the requested operation"),
    ERR_INTERNAL(-500, "Internal Server Error"),

    // Library-specific error messages
    // "URN urn:uuid:d5144314-7294-4593-9ca8-c517ed9d1f1e is of library element type library, and has no parent"
    // "Library element urn:uuid:9478f2b8-34d1-4583-99fe-61f556ba2b4a is of type PageEntry, and can have no children"
    // "Library element urn:uuid:da84a82d-73fe-4ce0-8f66-... is not of type PageEntry, and can have no attachments"
    ERR_LIBRARY_PARENT_NOT_FOUND(-70, "There is no library element matching the specified parent."),
    ERR_LIBRARY_NO_SUCH_ELEMENT_TYPE(-70, "There is no library element type called %s"),
    ERR_LIBRARY_WRONG_PARENT_TYPE(-71, "Library element type %s cannot be the parent to a library element of type %s");

    private final String formattedMessage;

    private final int code;

    Result(int code, String formattedMessage)
    {
        this.code = code;
        this.formattedMessage = formattedMessage;
    }

    public String getFormattedMessage()
    {
        return formattedMessage;
    }

    public int getCode()
    {
        return code;
    }

    public static Result translate(int code)
    {
        switch (code)
        {
            case 1:
                return OK;
            case 0:
                return ERR_FAILURE;
            case -1:
                return ERR_UNAUTHORIZED;
            case -2:
                return ERR_NO_SUCH_URN;
            case -3:
                return ERR_UNLICENSED_FEATURE;
            case -4:
                return ERR_UNKNOWN_ENTITY_TYPE;
            case -5:
                return ERR_MISSING_FIELD;
            case -6:
                return ERR_UNKNOWN_DEVICE_IDENTIFICATION;
            case -7:
                return ERR_ALREADY_EXISTS;
            case -8:
                return ERR_NOT_EXISTS;
            case -9:
                return ERR_UNKNOWN_ENTITY;
            case -10:
                return ERR_UNKNOWN_TYPE;
            case -11:
                return ERR_SESSION_ALREADY_CLOSED;
            case -12:
                return ERR_NO_SUCH_EMAIL;
            case -13:
                return ERR_NO_FILE_CONTENT;
            case -50:
                return ERR_EXTENSION_SECURITY_RESTRICTION;
            case -51:
                return ERR_EXTENSION_NO_ACCESS;
            case -500:
                return ERR_INTERNAL;
            default:
                throw new IllegalArgumentException("Unknown Result: " + code);
        }
    }
}
