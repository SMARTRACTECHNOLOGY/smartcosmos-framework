package net.smartcosmos.client.objects.file;

import net.smartcosmos.client.connectivity.ServerContext;

public final class FileFactory
{
    private FileFactory()
    {
    }

    /**
     * Creates a new instance of a file client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IFileClient createClient(ServerContext context)
    {
        return new FileClient(context);
    }
}
