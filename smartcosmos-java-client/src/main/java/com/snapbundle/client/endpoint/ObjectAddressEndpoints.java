package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public final class ObjectAddressEndpoints
{
    private ObjectAddressEndpoints()
    {
    }

    private static final String BASE = "/objects/object/%s/address";

    private static final String CREATE__PUT = BASE;

    private static final String UPDATE__POST = BASE.concat("/%s");

    private static final String FIND_LAST_N__GET = BASE.concat("?count=%s&view=%s");

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String DELETE__DELETE = BASE.concat("/%s");

    public static String create(String objectUrn)
    {
        return String.format(CREATE__PUT, objectUrn);
    }

    public static String delete(String objectUrn, String urn)
    {
        return String.format(DELETE__DELETE, objectUrn, urn);
    }

    public static String update(String objectUrn, String urn)
    {
        return String.format(UPDATE__POST, objectUrn, urn);
    }

    public static String findByUrn(String objectUrn, String urn)
    {
        return findByUrn(objectUrn, urn, ViewType.Standard);
    }

    public static String findByUrn(String objectUrn, String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, objectUrn, urn, viewType);
    }

    public static String findLast(int count)
    {
        return findLast(count, ViewType.Standard);
    }

    public static String findLast(int count, ViewType viewType)
    {
        return String.format(FIND_LAST_N__GET, count, viewType);
    }

}
