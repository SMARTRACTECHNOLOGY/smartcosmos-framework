package ${package}.smartcosmos.extension.server;

import ${package}.smartcosmos.extension.server.resource.pub.ExampleExtensionResource;
import ${package}.smartcosmos.extension.server.resource.secure.MoreInterestingExampleExtensionResource;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.resource.IResourceRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleExtensionEndpointsResourceRegistrar
        implements IResourceRegistrar
{

    private static final Logger LOG = LoggerFactory.getLogger(ExampleExtensionEndpointsResourceRegistrar.class);

    // NOTE TO EXTENSION DEVELOPER:
    // This is where you register your endpoints, in accordance with access settings in objects.yml
    public void registerResources(IContext context)
    {
        EndpointsFactory endpointsFactory = new EndpointsFactory();

        if (endpointsFactory.getExampleEndpoints())
        {
            context.getEnvironment().jersey().register(new ExampleExtensionResource(context));
        } else
        {
            LOG.info("Disabling example endpoints as configured in objects.yml");
        }

        if (endpointsFactory.getMoreInterestingExampleEndpoints())
        {
            context.getEnvironment().jersey().register(new MoreInterestingExampleExtensionResource(context));
        } else
        {
            LOG.info("Disabling moreInterestingExample endpoints as configured in objects.yml");
        }
    }
}
