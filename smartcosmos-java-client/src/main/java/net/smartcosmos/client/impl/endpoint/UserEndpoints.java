package net.smartcosmos.client.impl.endpoint;

import net.smartcosmos.util.json.ViewType;

public final class UserEndpoints
{
    private UserEndpoints()
    {
    }

    private static final String BASE = "/users";

    private static final String CREATE__PUT = BASE;

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_EMAIL__GET = BASE.concat("/user/%s?view=%s");

    private static final String MANAGE_PASSWORD__POST = BASE.concat("/user");

    private static final String UPDATE__POST = BASE;

    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__POST;
    }

    public static String managePassword()
    {
        return MANAGE_PASSWORD__POST;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByEmailAddress(String emailAddress)
    {
        return findByEmailAddress(emailAddress, ViewType.Standard);
    }

    public static String findByEmailAddress(String emailAddress, ViewType viewType)
    {
        return String.format(FIND_BY_EMAIL__GET, emailAddress, viewType);
    }
}
