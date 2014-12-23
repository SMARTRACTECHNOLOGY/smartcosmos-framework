package net.smartcosmos.client.common.registration;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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

import net.smartcosmos.Field;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationResponse
{
    private final String urn;

    private final String realm;

    private final String emailAddress;

    private final long lastModifiedTimestamp;

    public RegistrationResponse(JSONObject jsonObject) throws JSONException
    {
        this.urn = jsonObject.getString(Field.URN_FIELD);
        this.realm = jsonObject.getString(Field.REALM_FIELD);
        this.emailAddress = jsonObject.getString(Field.EMAIL_ADDRESS_FIELD);
        this.lastModifiedTimestamp = jsonObject.getLong(Field.LAST_MODIFIED_TIMESTAMP_FIELD);
    }

    public String getUrn()
    {
        return urn;
    }

    public String getRealm()
    {
        return realm;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public long getLastModifiedTimestamp()
    {
        return lastModifiedTimestamp;
    }
}
