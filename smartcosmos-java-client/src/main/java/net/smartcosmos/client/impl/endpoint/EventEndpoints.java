package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.model.event.EventType;
import net.smartcosmos.util.json.ViewType;

public final class EventEndpoints
{
    private EventEndpoints()
    {
    }

    private static final String BASE = "/events";

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_EVENT_TYPE__GET = BASE.concat("?eventType=%s&view=%s");

    private static final String FIND_BY_TIMESTAMP__GET = BASE.concat("?timestamp=%s&view=%s");

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByTimestamp(long timestamp)
    {
        return findSince(timestamp, ViewType.Standard);
    }

    public static String findSince(long timestamp, ViewType viewType)
    {
        return String.format(FIND_BY_TIMESTAMP__GET, timestamp, viewType);
    }

    public static String findByEventType(EventType eventType)
    {
        return findByEventType(eventType, ViewType.Standard);
    }

    public static String findByEventType(EventType eventType, ViewType viewType)
    {
        return String.format(FIND_BY_EVENT_TYPE__GET, eventType, viewType);
    }
}

