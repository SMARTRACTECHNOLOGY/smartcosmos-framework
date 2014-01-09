package com.snapbundle.client.endpoint;

public class RegistrationEndpoints implements IRegistrationEndpoints
{
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
