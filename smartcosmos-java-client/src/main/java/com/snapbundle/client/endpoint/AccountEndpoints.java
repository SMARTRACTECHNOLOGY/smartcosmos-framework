package com.snapbundle.client.endpoint;

public final class AccountEndpoints
{
    private AccountEndpoints()
    {
    }

    private static final String BASE = "/account";

    private static final String CHANGE_MY_PASSWORD__POST = BASE.concat("/password?resetPassword=false&setPassword=%s");

    private static final String RESET_MY_PASSWORD__POST = BASE.concat("/password?resetPassword=true");

    public static String changeMyPassword(String setPassword)
    {
        return String.format(CHANGE_MY_PASSWORD__POST, setPassword);
    }

    public static String resetMyPassword()
    {
        return RESET_MY_PASSWORD__POST;
    }
}
