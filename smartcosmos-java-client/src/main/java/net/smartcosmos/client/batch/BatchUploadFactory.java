package net.smartcosmos.client.batch;

import net.smartcosmos.client.connectivity.ServerContext;

public class BatchUploadFactory
{
    private BatchUploadFactory()
    {
    }

    /**
     * Creates a new instance of a batch upload client that will work with the specified server context.
     *
     * @param context Server connection information
     * @return New batch upload client instance
     */
    public static IBatchUploadClient createClient(ServerContext context)
    {
        return new BatchUploadClient(context);
    }
}
