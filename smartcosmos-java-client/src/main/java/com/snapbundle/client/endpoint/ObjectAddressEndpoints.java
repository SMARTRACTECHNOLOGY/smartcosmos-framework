package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public class ObjectAddressEndpoints implements IObjectAddressEndpoints
{
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
