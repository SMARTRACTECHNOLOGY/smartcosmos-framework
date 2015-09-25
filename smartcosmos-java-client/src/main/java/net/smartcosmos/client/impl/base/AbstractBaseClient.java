package net.smartcosmos.client.impl.base;

import java.util.Arrays;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import net.smartcosmos.client.connectivity.ServerContext;

public abstract class AbstractBaseClient
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    protected final ServerContext context;

    /**
     * Each base client has its own http client for accessing, whereas other ones utilize the same one passed in.
     */
    private final Client client;

    protected AbstractBaseClient(final ServerContext context)
    {
        this(context, new Client(Arrays.asList(Protocol.HTTP, Protocol.HTTPS)));
    }

    protected AbstractBaseClient(final ServerContext context, final Client client)
    {
        this.context = context;
        this.client = client;
    }

    protected Client getClient()
    {
        return client;
    }

    protected ClientResource createClient(final String path)
    {
        String assembledPath = assembleEndpoint(path);
        log.debug("Endpoint URL: " + assembledPath);

        final ClientResource service = new ClientResource(new Context(getClass().getName()), assembledPath);
        service.setNext(client);

        if (context.getEmailAddress() != null)
        {
            ChallengeScheme scheme = ChallengeScheme.HTTP_BASIC;

            ChallengeResponse authentication = new ChallengeResponse(scheme,
                    context.getEmailAddress(),
                    context.getCredentials());

            service.setChallengeResponse(authentication);
        }

        return service;
    }

    private String assembleEndpoint(final String path)
    {
        return context
                .getServerUrl()
                .concat(context.getContextPath())
                .concat(path);
    }
}
