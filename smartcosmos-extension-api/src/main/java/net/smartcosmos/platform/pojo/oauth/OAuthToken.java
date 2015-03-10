package net.smartcosmos.platform.pojo.oauth;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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
import net.smartcosmos.platform.api.oauth.IOAuthToken;

public final class OAuthToken implements IOAuthToken
{
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String bearerToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private long expiresInSecs;

    public OAuthToken(String tokenType, String bearerToken, long expiresInSecs, String refreshToken)
    {
        this.tokenType = tokenType;
        this.bearerToken = bearerToken;
        this.refreshToken = refreshToken;
        this.expiresInSecs = expiresInSecs;
    }


    @Override
    public String getTokenType()
    {
        return tokenType;
    }

    @Override
    public String getBearerToken()
    {
        return bearerToken;
    }

    @Override
    public String getRefreshToken()
    {
        return refreshToken;
    }

    @Override
    public long getExpiresInSecs()
    {
        return expiresInSecs;
    }
}
