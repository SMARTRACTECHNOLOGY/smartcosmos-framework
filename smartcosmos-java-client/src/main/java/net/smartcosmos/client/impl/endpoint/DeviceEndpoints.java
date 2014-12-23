package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.util.json.ViewType;

public final class DeviceEndpoints
{
    private DeviceEndpoints()
    {
    }

    private static final String BASE = "/devices";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_NAME_LIKE__GET = BASE.concat("?nameLike=%s&view=%s");

    private static final String FIND_BY_DEVICE_ID__GET = BASE.concat("/device/%s?view=%s");

    private static final String UPDATE__POST = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__POST;
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

    public static String findByDeviceIdentification(String identification)
    {
        return findByDeviceIdentification(identification, ViewType.Standard);
    }

    public static String findByDeviceIdentification(String identification, ViewType viewType)
    {
        return String.format(FIND_BY_DEVICE_ID__GET, identification, viewType);
    }

}
