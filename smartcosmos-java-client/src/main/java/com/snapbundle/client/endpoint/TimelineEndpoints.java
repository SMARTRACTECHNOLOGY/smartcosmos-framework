package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public final class TimelineEndpoints
{
    private TimelineEndpoints()
    {
    }

    /**
     * {entityReferenceType}/{referenceUrn}
     */
    private static final String BASE = "/timelines/%s/%s";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_NAME_LIKE__GET = BASE.concat("?nameLike=%s&view=%s");

    private static final String UPDATE__PUT = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__PUT;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByNameLike(String nameLike)
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }

    public static String findByNameLike(String nameLike, ViewType viewType)
    {
        return String.format(FIND_BY_NAME_LIKE__GET, nameLike, viewType);
    }
}
