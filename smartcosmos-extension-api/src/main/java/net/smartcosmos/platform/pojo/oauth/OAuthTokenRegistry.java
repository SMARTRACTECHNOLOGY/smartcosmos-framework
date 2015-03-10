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

import net.smartcosmos.platform.api.oauth.IOAuthTokenRegistry;
import net.smartcosmos.platform.api.oauth.OAuthStatusType;
import net.smartcosmos.pojo.base.DomainResource;

public final class OAuthTokenRegistry extends DomainResource<IOAuthTokenRegistry> implements IOAuthTokenRegistry
{
    private String name;

    private String description;

    private boolean activeFlag;

    private long oAuthTxTimestamp;

    private String state;

    private String csrfToken;

    private OAuthStatusType authorizationCodeRequestStatus;

    private String authorizationCode;

    private long authorizationCodeTimestamp;

    private String bearerToken;

    private String refreshToken;

    private long tokenExchangeTimestamp;

    private OAuthStatusType tokenExchangeRequestStatus;

    private long tokenRefreshTimestamp;

    private OAuthStatusType refreshTokenStatus;

    @Override
    public long getTokenRefreshTimestamp()
    {
        return tokenRefreshTimestamp;
    }

    @Override
    public OAuthStatusType getRefreshTokenStatus()
    {
        return refreshTokenStatus;
    }

    @Override
    public void setRefreshTokenStatus(OAuthStatusType tokenRefreshRequestStatus)
    {
        this.tokenRefreshTimestamp = System.currentTimeMillis();
        this.refreshTokenStatus = tokenRefreshRequestStatus;
    }

    @Override
    public long getOAuthTransactionTimestamp()
    {
        return oAuthTxTimestamp;
    }

    @Override
    public void setOAuthTransactionTimestamp(long timestamp)
    {
        this.oAuthTxTimestamp = timestamp;
    }

    @Override
    public void setCSRFToken(String csrfToken)
    {
        this.csrfToken = csrfToken;
    }

    @Override
    public String getCSRFToken()
    {
        return csrfToken;
    }

    @Override
    public void setState(String state)
    {
        this.state = state;
    }

    @Override
    public OAuthStatusType getAuthorizationCodeStatus()
    {
        return authorizationCodeRequestStatus;
    }

    public void setAuthorizationCodeStatus(OAuthStatusType authorizationCodeRequestStatus)
    {
        this.authorizationCodeRequestStatus = authorizationCodeRequestStatus;
    }

    @Override
    public String getCode()
    {
        return authorizationCode;
    }

    @Override
    public void setCode(String code)
    {
        this.authorizationCode = code;
        this.authorizationCodeTimestamp = System.currentTimeMillis();
    }

    @Override
    public String getState()
    {
        return state;
    }

    @Override
    public long getCodeIssueTimestamp()
    {
        return authorizationCodeTimestamp;
    }

    @Override
    public String getToken()
    {
        return bearerToken;
    }

    @Override
    public void setToken(String token)
    {
        this.bearerToken = token;
        this.tokenExchangeTimestamp = System.currentTimeMillis();
    }

    @Override
    public String getRefreshToken()
    {
        return refreshToken;
    }

    @Override
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    @Override
    public long getTokenExchangeTimestamp()
    {
        return tokenExchangeTimestamp;
    }

    @Override
    public OAuthStatusType getBearerTokenStatus()
    {
        return tokenExchangeRequestStatus;
    }

    @Override
    public void setBearerTokenStatus(OAuthStatusType tokenExchangeRequestStatus)
    {
        this.tokenExchangeRequestStatus = tokenExchangeRequestStatus;
        this.refreshTokenStatus = OAuthStatusType.ActiveToken;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public void copy(IOAuthTokenRegistry object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}

