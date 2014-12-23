package net.smartcosmos.client.objects.interaction.session;

import net.smartcosmos.client.connectivity.ServerContext;

public final class InteractionSessionFactory
{
    private InteractionSessionFactory()
    {
    }

    /**
     * Creates a new instance of an object interaction session client that will work with objects at the specified
     * server context.
     *
     * @param context Server connection information
     * @return New interaction session client instance
     */
    public static IInteractionSessionClient createClient(ServerContext context)
    {
        return new InteractionSessionClient(context);
    }
}
