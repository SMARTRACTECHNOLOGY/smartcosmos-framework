package net.smartcosmos.client.connectivity;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Defines a SMART COSMOS server context consisting of an email address, credentials, server location, and the
 * context path, where the credentials are optional in those situations where a public (non-protected) endpoint is
 * being invoked. Examples of public (non-protected) endpoints include the use of
 * {@link net.smartcosmos.client.common.registration.IRegistrationClient} and the encode/decode operations defined by
 * {@link net.smartcosmos.client.common.metadata.IMetadataClient}.
 * <p/>
 * The default {@link #getServerUrl()} is <b>https://objects.smartcosmos.net</b>
 * The default {@link #getContextPath()} is <b>/rest</b>
 */
public final class ServerContext
{
    static final Logger LOGGER = LoggerFactory.getLogger(ServerContext.class);

    public static final String DEFAULT_CONTEXT_PATH = "/rest";

    private final String server;

    private final String emailAddress;

    private final String credentials;

    private final String contextPath;

    /**
     * Defines a SMART COSMOS server context using the specified server location with no
     * authentication credentials (suitable for accessing public endpoints).
     *
     * @param server Server location, e.g. https://objects.example.com
     */
    public ServerContext(String server)
    {
        this(null, null, server);
    }

    /**
     * Defines a SMART COSMOS server context using a specific SMART COSMOS server location and the default context
     * path of /rest.
     *
     * @param emailAddress Email address
     * @param credentials  Credentials
     * @param server       Server location, e.g. https://objects.example.com
     */
    public ServerContext(String emailAddress, String credentials, String server)
    {
        this(emailAddress, credentials, server, DEFAULT_CONTEXT_PATH);
    }

    /**
     * Defines a SMART COSMOS server context using a specific SMART COSMOS server location and a specific context
     * path prepended in front of the well-known client endpoints.
     *
     * @param emailAddress Email address
     * @param credentials  Credentials
     * @param server       Server location, e.g. https://objects.example.com
     * @param contextPath  Prepended context path in front of all client endpoints, e.g. /rest
     */
    public ServerContext(String emailAddress, String credentials, String server, String contextPath)
    {
        checkNotNull(server, "parameter 'server' must be a valid server location, e.g. https://objects.example.com");
        checkNotNull(contextPath, "parameter 'contextPath' must be a valid context path, e.g. /rest");


        if (emailAddress == null && credentials == null)
        {
            LOGGER.info("Creating an anonymous client suitable for accessing a public endpoint");
        } else
        {
            LOGGER.info("Creating an authenticated client using email address " + emailAddress);
        }

        this.emailAddress = emailAddress;
        this.credentials = credentials;
        this.server = server;
        this.contextPath = contextPath;

        LOGGER.info("Server Endpoint: " + this.server);
        LOGGER.info("Context Path Prefix: " + this.contextPath);
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getCredentials()
    {
        return credentials;
    }

    public String getServerUrl()
    {
        return server;
    }

    public String getContextPath()
    {
        return contextPath;
    }
}
