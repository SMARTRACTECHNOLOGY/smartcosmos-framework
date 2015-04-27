package net.smartcosmos.platform.api.oauth;

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

import net.smartcosmos.platform.api.IObjectsContext;

/**
 * Provides 3rd party (e.g. DropBox, Box, etc.) OAuth 2.0 bearer and refresh token management services. Each token
 * manager is declared in the .yml configuration file as key-value property pairs underneath the
 * <b>oauthTokenManagers</b> parent key. For example:
 * <p/>
 * <pre>
 * oauthTokenManagers:
 * <nbsp/><nbsp/>Box.com : net.smartcosmos.objects.job.tokenmanager.BoxOAuthTokenManager
 * </pre>
 * <p/>
 * <B>NOTE:</B> It is critical that the value of the {@link #getName()} method return an exact match to the key
 * identified under the <b>oauthTokenManagers</b>.
 */
public interface IExternalOAuthTokenManager
{
    /**
     * SMART COSMOS platform runtime context.
     *
     * @param context runtime context
     */
    void setContext(IObjectsContext context);

    /**
     * OAuth Token Registry represents the single OAuth 2.0 record that either needs to be refreshed or revoked.
     *
     * @param registry token registry
     */
    void setOAuthTokenRegistry(IOAuthTokenRegistry registry);

    /**
     * Case-sensitive name assigned to the external service accessed using OAuth 2.0 bearer tokens.
     * <p/>
     * <b>NOTE:</b> The name assigned here must be a case-sensitive exact match against the entry in the .yml file
     * under the <i>oauthTokenManagers</i> parent key.
     *
     * @return Service name
     */
    String getName();

    void authorizationRequestHandler();

    void authorizationResponseHandler();

    /**
     * Invoked automatically by a Quartz job, injecting the
     * {@link net.smartcosmos.platform.api.oauth.IOAuthTokenRegistry} that requires a token refresh using the refresh
     * token to ensure that the granted authorization doesn't expire.
     */
    void tokenRefreshHandler();

    void tokenRevokeHandler();
}
