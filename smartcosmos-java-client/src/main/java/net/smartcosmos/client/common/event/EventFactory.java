package net.smartcosmos.client.common.event;

import net.smartcosmos.client.connectivity.ServerContext;

public final class EventFactory
{
    private EventFactory()
    {
    }

    /**
     * Creates a new instance of an event client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IEventClient createClient(ServerContext context)
    {
        return new EventClient(context);
    }
}
