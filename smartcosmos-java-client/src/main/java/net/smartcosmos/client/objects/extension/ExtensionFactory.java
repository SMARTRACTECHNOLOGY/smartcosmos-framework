package net.smartcosmos.client.objects.extension;

import net.smartcosmos.client.connectivity.ServerContext;

public final class ExtensionFactory
{
    private ExtensionFactory()
    {
    }

    /**
     * Creates a new instance of an extension client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IExtensionClient createClient(ServerContext context)
    {
        return new ExtensionClient(context);
    }
}
