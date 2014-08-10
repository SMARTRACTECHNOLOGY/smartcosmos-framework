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

package net.smartcosmos.client.connectivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a SnapBundle server context consisting of an email address, credentials, and a server location, where the
 * credentials are optional in those situations where a public (non-protected) endpoint is being invoked. Examples of
 * public (non-protected) endpoints include the use of {@link net.smartcosmos.client.registration.IRegistrationClient}
 * and the encode/decode operations defined by {@link net.smartcosmos.client.metadata.IMetadataClient}.
 * <p/>
 * The default {@link #getServerUrl()} is <b>https://snapbundle.tagdynamics.net</b>
 */
public final class ServerContext
{
    final static Logger LOGGER = LoggerFactory.getLogger(ServerContext.class);

    // TODO: Update to HTTPS once the cert is installed at the AWS load balancer
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
