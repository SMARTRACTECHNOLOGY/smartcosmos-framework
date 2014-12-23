package net.smartcosmos.client.objects.timeline;

import net.smartcosmos.client.connectivity.ServerContext;

public final class TimelineFactory
{
    private TimelineFactory()
    {
    }

    /**
     * Creates a new instance of a timeline client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static ITimelineClient createClient(ServerContext context)
    {
        return new TimelineClient(context);
    }
}
