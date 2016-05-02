package net.smartcosmos.platform.configuration;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
 * ===============================================================================
 * Copyright (C) 2013 - 2016 Smartrac Technology Fletcher, Inc.
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
import net.smartcosmos.platform.constraint.annotation.CredentialsNotNullIfAuthEnabled;

@CredentialsNotNullIfAuthEnabled
public class SmartCosmosMetricsFactory
{
    @JsonProperty
    private Boolean enabled = false;

    @JsonProperty("basicAuth")
    private Boolean authenticationEnabled = false;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    public Boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Boolean isAuthenticationEnabled()
    {
        return authenticationEnabled;
    }

    public void setAuthenticationEnabled(Boolean authenticationEnabled)
    {
        this.authenticationEnabled = authenticationEnabled;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "SmartCosmosMetricsFactory{" +
                "enabled=" + enabled +
                ", basicAuth=" + authenticationEnabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
