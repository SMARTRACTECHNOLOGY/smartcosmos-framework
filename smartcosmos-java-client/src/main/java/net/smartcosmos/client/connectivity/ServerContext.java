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

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a SMART COSMOS server context consisting of an email address, credentials, and a server location, where the
 * credentials are optional in those situations where a public (non-protected) endpoint is being invoked. Examples of
 * public (non-protected) endpoints include the use of {@link net.smartcosmos.client.common.registration.IRegistrationClient}
 * and the encode/decode operations defined by {@link net.smartcosmos.client.common.metadata.IMetadataClient}.
 * <p/>
 * The default {@link #getServerUrl()} is <b>https://objects.smartcosmos.net</b>
 */
public final class ServerContext
{
    final static Logger LOGGER = LoggerFactory.getLogger(ServerContext.class);

    private String server;

    private String emailAddress;

    private String credentials;

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
     * Defines a SMART COSMOS server context using a specific SMART COSMOS server location.
     *
     * @param emailAddress Email address
     * @param credentials  Credentials
     * @param server       Server location, e.g. https://objects.example.com
     */
    public ServerContext(String emailAddress, String credentials, String server)
    {
        Preconditions.checkNotNull(server, "parameter 'server' must be a valid server location, e.g. https://objects.example.com");
        LOGGER.info("Server Endpoint: " + this.server);

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
}
