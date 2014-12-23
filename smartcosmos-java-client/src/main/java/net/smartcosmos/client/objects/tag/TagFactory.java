package net.smartcosmos.client.objects.tag;

import net.smartcosmos.client.connectivity.ServerContext;

public final class TagFactory
{
    private TagFactory()
    {
    }

    /**
     * Creates a new instance of a tag client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static ITagClient createClient(ServerContext context)
    {
        return new TagClient(context);
    }
}