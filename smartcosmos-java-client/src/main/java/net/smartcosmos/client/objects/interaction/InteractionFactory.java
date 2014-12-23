package net.smartcosmos.client.objects.interaction;

import net.smartcosmos.client.connectivity.ServerContext;

public final class InteractionFactory
{
    private InteractionFactory()
    {
    }

    /**
     * Creates a new instance of an object interaction client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IInteractionClient createClient(ServerContext context)
    {
        return new InteractionClient(context);
    }
}
