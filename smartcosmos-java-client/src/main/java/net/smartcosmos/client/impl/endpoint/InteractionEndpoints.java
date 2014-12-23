package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.util.json.ViewType;

public final class InteractionEndpoints
{
    private InteractionEndpoints()
    {
    }

    private static final String BASE = "/interactions";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String LIST_ALL__GET = BASE.concat("?view=%s");

    private static final String FIND_BY_OBJECT_URN__GET = BASE.concat("?objectUrn=%s&view=%s");

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String listAllInteractions()
    {
        return listAllInteractions(ViewType.Standard);
    }

    public static String listAllInteractions(ViewType viewType)
    {
        return String.format(LIST_ALL__GET, viewType);
    }

    public static String findByObjectUrn(String objectUrnLike)
    {
        return findByObjectUrn(objectUrnLike, ViewType.Standard);
    }

    public static String findByObjectUrn(String objectUrnLike, ViewType viewType)
    {
        return String.format(FIND_BY_OBJECT_URN__GET, objectUrnLike, viewType);
    }

}
