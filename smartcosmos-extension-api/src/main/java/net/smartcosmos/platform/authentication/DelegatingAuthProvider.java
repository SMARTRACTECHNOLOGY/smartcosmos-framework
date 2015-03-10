/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.platform.authentication;

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

import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import org.eclipse.jetty.util.B64Code;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Dispatching Jersey authentication provider that inspects the Authorization header for the presence of
 * an OAuth 2 bearer token, delegating the authentication to an OAuth 2 {@link io.dropwizard.auth.Authenticator} or,
 * , if no bearer token is present, delegating the authentication to an HTTP Basic
 * {@link io.dropwizard.auth.Authenticator}.
 * <p/>
 * The primary benefit for this provider is to support multiple authentication types concurrently at a single endpoint.
 *
 * @param <T> the principal type.
 */
public final class DelegatingAuthProvider<T> implements InjectableProvider<Auth, Parameter>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DelegatingAuthProvider.class);

    private final Authenticator<BasicCredentials, T> basicAuthenticator;

    private final Authenticator<String, T> oauthAuthenticator;

    private final String realm;

    private final class DelegatingAuthInjectable<T> extends AbstractHttpContextInjectable<T>
    {
        private static final String BEARER_PREFIX = "bearer";

        private static final String BASIC_PREFIX = "Basic";

        private static final String CHALLENGE_FORMAT = "realm=\"%s\"";

        private final Authenticator<BasicCredentials, T> basicAuthenticator;

        private final Authenticator<String, T> oauthAuthenticator;

        private final String realm;

        private final boolean required;

        private DelegatingAuthInjectable(Authenticator<BasicCredentials, T> basicAuthenticator,
                                         Authenticator<String, T> oauthAuthenticator,
                                         String realm,
                                         boolean required)
        {
            this.basicAuthenticator = basicAuthenticator;
            this.oauthAuthenticator = oauthAuthenticator;
            this.realm = realm;
            this.required = required;
        }

        @Override
        public T getValue(HttpContext c)
        {
            final String header = c.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
            try
            {
                if (header != null)
                {
                    final int space = header.indexOf(' ');
                    if (space > 0)
                    {
                        final String method = header.substring(0, space);

                        if (BASIC_PREFIX.equalsIgnoreCase(method))
                        {
                            final String decoded = B64Code.decode(header.substring(space + 1),
                                    StringUtil.__ISO_8859_1);
                            final int i = decoded.indexOf(':');
                            if (i > 0)
                            {
                                final String username = decoded.substring(0, i);
                                final String password = decoded.substring(i + 1);
                                final BasicCredentials credentials = new BasicCredentials(username,
                                        password);
                                final Optional<T> result = basicAuthenticator.authenticate(credentials);
                                if (result.isPresent())
                                {
                                    return result.get();
                                }
                            }
                        } else if (BEARER_PREFIX.equalsIgnoreCase(method))
                        {
                            final String credentials = header.substring(space + 1);
                            final Optional<T> result = oauthAuthenticator.authenticate(credentials);
                            if (result.isPresent())
                            {
                                return result.get();
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException e)
            {
                LOGGER.debug("Error decoding credentials", e);
            } catch (AuthenticationException e)
            {
                LOGGER.warn("Error authenticating credentials", e);
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }

            if (required)
            {
                MultivaluedMap<String, String> headers = c.getRequest().getRequestHeaders();

                final String challenge = String.format(CHALLENGE_FORMAT, realm);

                for (String key : headers.keySet())
                {
                    String value = headers.getFirst(key);
                    if (value != null && value.equalsIgnoreCase("application/json"))
                    {
                        //
                        // This is probably a machine client - present JSON

                        throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                                .header(HttpHeaders.WWW_AUTHENTICATE,
                                        challenge)
                                .entity(ResponseEntity.toJson(Result.ERR_MISSING_AUTHENTICATION_HEADER))
                                .type(MediaType.APPLICATION_JSON_TYPE)
                                .build());
                    }
                }

                //
                // This could be a Web Browser - present a plain text response
                //

                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                challenge)
                        .entity("Credentials are required to access this resource.")
                        .type(MediaType.TEXT_PLAIN_TYPE)
                        .build());
            }

            return null;
        }

    }

    /**
     * Creates a new DelegatingAuthProvider with the given HTTP Basic and OAuth 2 authenticators to whom
     * authentication will be delegated to based strictly on the presence (or lack thereof) of an OAuth 2
     * bearer access token in the HTTP Authorization header.
     *
     * @param basicAuthenticator HTTP Basic authenticator
     * @param oauthAuthenticator OAuth 2 authenticator
     */
    public DelegatingAuthProvider(Authenticator<BasicCredentials, T> basicAuthenticator,
                                  Authenticator<String, T> oauthAuthenticator,
                                  String realm)
    {
        this.basicAuthenticator = basicAuthenticator;
        this.oauthAuthenticator = oauthAuthenticator;
        this.realm = realm;
    }

    @Override
    public ComponentScope getScope()
    {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext ic, Auth auth, Parameter parameter)
    {
        return new DelegatingAuthInjectable<>(basicAuthenticator, oauthAuthenticator, realm, auth.required());
    }
}
