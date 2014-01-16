package com.snapbundle.client.endpoint;

public final class OAuthEndpoints
{
    private OAuthEndpoints()
    {
    }

    private static final String BASE = "/oauth2";

    public static String exchangeCode()
    {
        return BASE.concat("/token");
    }

}
