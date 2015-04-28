package net.smartcosmos.platform.api.oauth;

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

public enum OAuthStatusType
{
    NoneIssued("none_issued", "No authorization code was issued under this transaction- it is a refresh"),
    PendingUserApproval("pending", "The request is presently pending authorization from the user"),
    PendingExchange("pending",
            "The authorization code hasn't been exchanged yet for a bearer access and refresh token pair"),

    /* Authorization Code Errors */
    InvalidRequest("invalid_request",
            "The request is missing a required parameter, includes an invalid parameter value, includes a parameter " +
                    "more than once, or is otherwise malformed."),
    InvalidRequest__UnrecognizedClient("invalid_request",
            "The request format is valid, but the specified client_id is unrecognized."),
    InvalidRequest__MissingScope("invalid_request",
            "The request is missing a required parameter, scope, which is used to define what type of account access " +
                    "is being requested."),
    UnsupportedResponseType("unsupported_response_type",
            "The authorization server does not support obtaining an authorization code using this method. Check the " +
                    "value of the code param in your request."),
    AccessDenied("access_denied", "The resource owner or authorization server denied the request"),
    AuthorizationCodeIssued("success", "Authorization code has been issued"),

    /* Token Exchange Errors */
    InvalidRequest_InvalidGrantType("invalid_request", "Invalid grant_type parameter or parameter missing."),
    InvalidRequest_MissingParameter("invalid_request", "Missing parameter. 'code' is required"),
    InvalidRequest_NoSuchToken("invalid_request", "No such token"),
    UnauthorizedClient("unauthorized_client", "The grant type is unauthorized for this client_id"),
    InvalidGrant_InvalidAuthorizationCode("invalid_grant",
            "Authorization code doesn't exist or is invalid for the client"),
    InvalidGrant_ExpiredAuthorizationCode("invalid_grant", "Authorization code has expired"),
    InvalidClient("invalid_client", "The client credentials specified are invalid"),
    RedirectUriMismatch("redirect_uri_mismatch",
            "The specified redirect URI is missing or does not match what has been " +
                    "registered with the extension point"),
    InsecureRedirectUri("insecure_redirect_uri",
            "The URI is either not a custom protocol or not HTTPS, and it is doesn't begin with http://localhost " +
                    "for developer testing"),
    InvalidRedirectUri("invalid_redirect_uri",
            "The redirect_uri scheme is invalid per the RFC. A common problem is the first character " +
                    "being something besides a letter"),

    /* Pre token exchange status codes */
    ActiveToken("active", "Authorization code has been exchanged for a bearer access token"),
    StaleToken_Refreshed("stale", "The refresh token has been used, rendering this token stale"),
    StaleToken_Expired("expired", "The bearer access token lifespan has expired"),
    StaleToken_AuthorizingUserDisabled("authorization_failure",
            "The account's authorizing user has since become disabled; access token is no longer considered valid"),

    /* Post token exchange status codes */
    RevokedToken("revoked", "The extension's authorization represented by this token has since been revoked"),
    InvalidRequest_RefreshTokenExpired("invalid_request", "The refresh token has expired or is malformed"),
    Refreshed("refreshed", "Refresh token has been successfully used to issue a new token pair");

    private final String errorDescription;

    private final String error;

    OAuthStatusType(String error, String errorDescription)
    {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }

    public String getError()
    {
        return error;
    }
}
