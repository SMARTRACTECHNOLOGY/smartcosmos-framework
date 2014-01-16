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

    public static String revokeBearerCode()
    {
        return BASE.concat("/revoke/bearer");
    }

    public static String revokeRefreshCode()
    {
        return BASE.concat("/revoke/refresh");
    }

}
