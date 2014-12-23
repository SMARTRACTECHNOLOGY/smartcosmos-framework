package net.smartcosmos.client.impl.endpoint;

public final class RegistrationEndpoints
{
    private RegistrationEndpoints()
    {
    }

    private static final String BASE = "/registration";

    private static final String CREATE_REGISTRATION__PUT = BASE.concat("/register");

    private static final String CHECK_REALM_AVAILABILITY__GET = BASE.concat("/realm/%s");

    private static final String CONFIRM_REGISTRATION__GET = BASE.concat("/confirm/%s/%s");

    public static String checkRealmAvailability(String realm)
    {
        return String.format(CHECK_REALM_AVAILABILITY__GET, realm);
    }

    public static String registration()
    {
        return CREATE_REGISTRATION__PUT;
    }

    public static String confirmRegistration(String emailVerificationToken, String emailAddress)
    {
        return String.format(CONFIRM_REGISTRATION__GET, emailVerificationToken, emailAddress);
    }
}
