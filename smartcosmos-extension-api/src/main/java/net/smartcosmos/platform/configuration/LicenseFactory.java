package net.smartcosmos.platform.configuration;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class LicenseFactory
{
    public static final String UNLIMITED = "unlimited";

    @NotEmpty
    @JsonProperty
    private String edition;

    @JsonProperty
    private String editionKey;

    @JsonProperty
    private String enterpriseKey;

    @JsonProperty
    private String enterpriseKeySignature;

    @NotEmpty
    @JsonProperty
    private String objectCount;

    public String getEdition()
    {
        return edition;
    }

    public void setEdition(String edition)
    {
        this.edition = edition;
    }

    public String getEditionKey()
    {
        return editionKey;
    }

    public void setEditionKey(String editionKey)
    {
        this.editionKey = editionKey;
    }

    public String getEnterpriseKey()
    {
        return enterpriseKey;
    }

    public void setEnterpriseKey(String enterpriseKey)
    {
        this.enterpriseKey = enterpriseKey;
    }

    public String getEnterpriseKeySignature()
    {
        return enterpriseKeySignature;
    }

    public void setEnterpriseKeySignature(String enterpriseKeySignature)
    {
        this.enterpriseKeySignature = enterpriseKeySignature;
    }

    public String getObjectCount()
    {
        return objectCount;
    }

    public void setObjectCount(String objectCount)
    {
        this.objectCount = objectCount;
    }

    public long getMaxObjects()
    {
        if (getObjectCount().equalsIgnoreCase(UNLIMITED))
        {
            return Long.MAX_VALUE;
        } else
        {
            try
            {
                return Long.valueOf(getObjectCount());
            } catch (NumberFormatException e)
            {
                //
                // FAIL SAFE - if a bad number is entered, default to 100 to be safe, not unlimited
                return 100;
            }
        }
    }
}
