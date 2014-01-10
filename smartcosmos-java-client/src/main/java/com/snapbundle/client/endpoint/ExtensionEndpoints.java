package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public class ExtensionEndpoints implements IExtensionEndpoints
{
    public static String create()
    {
        return CREATE__PUT;
    }

    public static String delete(String urn)
    {
        return String.format(DELETE__DELETE, urn);
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

}
