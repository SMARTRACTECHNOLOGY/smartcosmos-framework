package com.snapbundle.client.endpoint;

public final class AccountEndpoints
{
    private AccountEndpoints()
    {
    }

    private static final String BASE = "/account";

    private static final String CHANGE_MY_PASSWORD__POST = BASE.concat("/password/change");

    private static final String RESET_MY_PASSWORD__POST = BASE.concat("/password/reset");

    public static String changeMyPassword()
    {
        return String.format(CHANGE_MY_PASSWORD__POST);
    }

    public static String resetMyPassword()
    {
        return RESET_MY_PASSWORD__POST;
    }
}
