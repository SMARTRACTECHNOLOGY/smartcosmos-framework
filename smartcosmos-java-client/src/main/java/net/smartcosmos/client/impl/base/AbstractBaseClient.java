package net.smartcosmos.client.impl.base;

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
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBaseClient
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseClient.class);

    protected final ServerContext context;

    protected AbstractBaseClient(ServerContext context)
    {
        this.context = context;
    }

    protected ClientResource createClient(String path)
    {
        String assembledPath = assembleEndpoint(path);
        LOG.debug("Endpoint URL: " + assembledPath);

        ClientResource service = new ClientResource(assembledPath);

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

    private String assembleEndpoint(String path)
    {
        return context
                .getServerUrl()
                .concat(context.getContextPath())
                .concat(path);
    }
}
