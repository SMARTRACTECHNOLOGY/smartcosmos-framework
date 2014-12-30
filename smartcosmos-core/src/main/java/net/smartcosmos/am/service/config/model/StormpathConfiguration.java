package net.smartcosmos.am.service.config.model;

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

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StormpathConfiguration
{
    @JsonProperty
    private boolean enabled;
    @JsonProperty
    @NotNull
    private String apiKeyId;
    @JsonProperty
    @NotNull
    private String apiKeySecret;
    @JsonProperty
    @NotNull
    private String applicationRestUrl;

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getApiKeyId()
    {
        return apiKeyId;
    }

    public void setApiKeyId(String apiKeyId)
    {
        this.apiKeyId = apiKeyId;
    }

    public String getApiKeySecret()
    {
        return apiKeySecret;
    }

    public void setApiKeySecret(String apiKeySecret)
    {
        this.apiKeySecret = apiKeySecret;
    }

    public String getApplicationRestUrl()
    {
        return applicationRestUrl;
    }

    public void setApplicationRestUrl(String applicationRestUrl)
    {
        this.applicationRestUrl = applicationRestUrl;
    }

}
