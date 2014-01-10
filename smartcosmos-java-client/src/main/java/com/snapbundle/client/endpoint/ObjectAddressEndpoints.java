package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public final class ObjectAddressEndpoints
{
    private ObjectAddressEndpoints()
    {
    }

    private static final String BASE = "/objects/object/%s/address";

    private static final String CREATE__PUT = BASE;

    private static final String UPDATE__POST = BASE;

    private static final String FIND_LAST_N__GET = BASE.concat("?count=%s&view=%s");

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String DELETE__DELETE = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String delete()
    {
        return DELETE__DELETE;
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

    public static String findLast(int count)
    {
        return findLast(count, ViewType.Standard);
    }

    public static String findLast(int count, ViewType viewType)
    {
        return String.format(FIND_LAST_N__GET, count, viewType);
    }

}
