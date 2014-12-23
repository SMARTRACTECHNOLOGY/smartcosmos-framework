package net.smartcosmos.client.impl.base;

import net.smartcosmos.client.connectivity.ServerContext;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBaseClient
{
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractBaseClient.class);

    protected final ServerContext context;

    protected AbstractBaseClient(ServerContext context)
    {
        this.context = context;
    }

    protected ClientResource createClient(String path)
    {
        ClientResource service = new ClientResource(assembleEndpoint(path));

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
        return context.getServerUrl().concat(path);
    }
}
