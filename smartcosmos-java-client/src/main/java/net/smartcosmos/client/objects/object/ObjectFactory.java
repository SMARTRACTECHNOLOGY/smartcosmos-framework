package net.smartcosmos.client.objects.object;

import net.smartcosmos.client.connectivity.ServerContext;

/**
 * Object Client Factory.
 */
public final class ObjectFactory
{
    private ObjectFactory()
    {
    }

    /**
     * Creates a new instance of a object client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New object client instance
     */
    public static IObjectClient createClient(ServerContext context)
    {
        return new ObjectClient(context);
    }
}
