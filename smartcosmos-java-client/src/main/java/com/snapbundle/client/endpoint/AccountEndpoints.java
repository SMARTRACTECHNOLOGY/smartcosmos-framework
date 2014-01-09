package com.snapbundle.client.endpoint;

public class AccountEndpoints implements IAccountEndpoints
{
    public static String changeMyPassword(String setPassword)
    {
        return String.format(CHANGE_MY_PASSWORD__POST, setPassword);
    }

    public static String resetMyPassword()
    {
        return RESET_MY_PASSWORD__POST;
    }
}
