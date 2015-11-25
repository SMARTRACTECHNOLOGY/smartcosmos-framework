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
import com.google.common.collect.Maps;
import net.smartcosmos.platform.api.Duration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public final class OAuth2Factory
{
    @Valid
    @NotNull
    @JsonProperty
    private Duration maxRefreshTokenLife = Duration.days(14);

    @Valid
    @NotNull
    @JsonProperty
    private Duration maxAuthenticationCodeLife = Duration.seconds(30);

    @Valid
    @NotNull
    @JsonProperty
    private Duration maxBearerTokenLife = Duration.minutes(60);

    @NotNull
    private Map<String, String> oauthTokenManagers = Maps.newLinkedHashMap();

    public Map<String, String> getOAuthTokenManagers()
    {
        return oauthTokenManagers;
    }

    public long getMaxAuthenticationCodeLifeMillis()
    {
        return maxAuthenticationCodeLife.toMilliseconds();
    }

    public long getMaxBearerTokenLifeMillis()
    {
        return maxBearerTokenLife.toMilliseconds();
    }

    public long getMaxRefreshTokenLifeMillis()
    {
        return maxRefreshTokenLife.toMilliseconds();
    }
}
