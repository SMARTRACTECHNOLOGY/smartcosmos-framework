/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a SnapBundle server context consisting of an email address, credentials, and a server location.
 */
public final class ServerContext
{
    final static Logger LOGGER = LoggerFactory.getLogger(ServerContext.class);

    private String server = "http://snapbundle.tagdynamics.net:8080";

    private String emailAddress;

    private String credentials;

    /**
     * Defines a SnapBundle server context using the default SnapBundle server location with no
     * authentication credentials (suitable for accessing public endpoints).
     */
    public ServerContext()
    {
        this(null, null, null);

    }

    /**
     * Defines a SnapBundle server context using the default SnapBundle server location.
     *
     * @param emailAddress Email address
     * @param credentials  Credentials
     */
    public ServerContext(String emailAddress, String credentials)
    {
        this(emailAddress, credentials, null);
    }

    /**
     * Defines a SnapBundle server context using a specific SnapBundle server location.
     *
     * @param emailAddress Email address
     * @param credentials  Credentials
     * @param server       Custom server location
     */
    public ServerContext(String emailAddress, String credentials, String server)
    {
        if (emailAddress == null && credentials == null)
        {
            LOGGER.info("Creating an anonymous client suitable for accessing a public endpoint");
        } else
        {
            LOGGER.info("Creating an authenticated client using email address " + emailAddress);
        }

        this.emailAddress = emailAddress;
        this.credentials = credentials;

        if (server != null)
        {
            this.server = server;
        }

        LOGGER.info("Server Endpoint: " + this.server);
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
