package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public final class EventEndpoints
{
    private EventEndpoints()
    {
    }

    private static final String BASE = "/events";

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_QUERY__POST = BASE;

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByQuery()
    {
        return findByUrn(ViewType.Standard);
    }

    public static String findByUrn(ViewType viewType)
    {
        return String.format(FIND_BY_QUERY__POST, viewType);
    }

}

