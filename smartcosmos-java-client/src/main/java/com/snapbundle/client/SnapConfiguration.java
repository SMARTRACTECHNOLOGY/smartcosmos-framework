package com.snapbundle.client;

public final class SnapConfiguration
{
    private SnapConfiguration()
    {
    }

    private static String server = "https://snapbundle.tagdynamics.net";

    private static String emailAddress;

    private static String credentials;

    public static void setSnapBundleServer(String server)
    {
        SnapConfiguration.server = server;
    }

    public static String SERVER_BASE_URL()
    {
        return server;
    }

    public static String getEmailAddress()
    {
        return emailAddress;
    }

    public static void setEmailAddress(String emailAddress)
    {
        SnapConfiguration.emailAddress = emailAddress;
    }

    public static String getCredentials()
    {
        return credentials;
    }

    public static void setCredentials(String credentials)
    {
        SnapConfiguration.credentials = credentials;
    }
}
