package net.smartcosmos.client.common.metadata;

import net.smartcosmos.client.connectivity.ServerContext;

public final class MetadataFactory
{
    private MetadataFactory()
    {
    }

    /**
     * Creates a new instance of a metadata client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IMetadataClient createClient(ServerContext context)
    {
        return new MetadataClient(context);
    }
}
