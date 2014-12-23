package net.smartcosmos.client.objects.object.address;

import net.smartcosmos.client.connectivity.ServerContext;

public final class ObjectAddressFactory
{
    private ObjectAddressFactory()
    {
    }

    /**
     * Creates a new instance of an object address client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IObjectAddressClient createClient(ServerContext context)
    {
        return new ObjectAddressClient(context);
    }
}
