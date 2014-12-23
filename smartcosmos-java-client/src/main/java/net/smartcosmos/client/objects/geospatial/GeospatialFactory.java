package net.smartcosmos.client.objects.geospatial;

import net.smartcosmos.client.connectivity.ServerContext;

public final class GeospatialFactory
{
    private GeospatialFactory()
    {
    }

    /**
     * Creates a new instance of a geospatial client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IGeospatialClient createClient(ServerContext context)
    {
        return new GeospatialClient(context);
    }
}
