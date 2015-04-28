package net.smartcosmos.platform.pojo.oauth;

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

import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.platform.api.oauth.IOAuthTokenTransaction;
import net.smartcosmos.platform.api.oauth.OAuthStatusType;
import net.smartcosmos.pojo.base.DomainResource;

public final class OAuthTokenTransaction extends DomainResource<IOAuthTokenTransaction>
        implements IOAuthTokenTransaction
{
    private long oAuthTxTimestamp;

    private String state;

    private String csrfToken;

    private OAuthStatusType authorizationCodeRequestStatus;

    private IUser authorizingUser;

    private String authorizationCode;

    private long authorizationCodeTimestamp;

    private String bearerToken;

    private String refreshToken;

    private long tokenExchangeTimestamp;

    private OAuthStatusType tokenExchangeRequestStatus;

    private long tokenRefreshTimestamp;

    private OAuthStatusType refreshTokenStatus;

    private String descendantTokenUrn;

    private int extensionVersion;

    private IExtension extension;


    @Override
    public long getTokenRefreshTimestamp()
    {
        return tokenRefreshTimestamp;
    }

    @Override
    public OAuthStatusType getRefreshTokenStatus()
    {
        return refreshTokenStatus;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRefreshTokenStatus(OAuthStatusType tokenRefreshRequestStatus)
    {
        this.tokenRefreshTimestamp = System.currentTimeMillis();
        this.refreshTokenStatus = tokenRefreshRequestStatus;
    }

    @Override
    public String getDescendantTokenUrn()
    {
        return descendantTokenUrn;
    }

    @Override
    public void setDescendantTokenUrn(String descendantTokenUrn)
    {
        this.descendantTokenUrn = descendantTokenUrn;
    }

    public OAuthTokenTransaction()
    {
        oAuthTxTimestamp = System.currentTimeMillis();
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
    public void setExtension(IExtension extension)
    {
        this.extension = extension;
    }

    @Override
    public IExtension getExtension()
    {
        return extension;
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
    public IUser getAuthorizingUser()
    {
        return authorizingUser;
    }

    @Override
    public void setAuthorizingUser(IUser user)
    {
        this.authorizingUser = user;
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
    public int getExtensionVersion()
    {
        return extensionVersion;
    }

    @Override
    public void setExtensionVersion(int version)
    {
        this.extensionVersion = version;
    }

    @Override
    public void copy(IOAuthTokenTransaction object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
