package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.util.json.ViewType;

public final class AccountEndpoints
{
    private AccountEndpoints()
    {
    }

    private static final String BASE = "/account";

    private static final String VIEW__GET = BASE.concat("?view=%s");

    private static final String CHANGE_MY_PASSWORD__POST = BASE.concat("/password/change");

    private static final String RESET_MY_PASSWORD__POST = BASE.concat("/password/reset");

    public static String view()
    {
        return view(ViewType.Standard);
    }

    public static String view(ViewType viewType)
    {
        return String.format(VIEW__GET, viewType);
    }

    public static String changeMyPassword()
    {
        return String.format(CHANGE_MY_PASSWORD__POST);
    }

    public static String resetMyPassword()
    {
        return RESET_MY_PASSWORD__POST;
    }
}
