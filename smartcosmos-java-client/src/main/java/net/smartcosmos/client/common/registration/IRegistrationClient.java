package net.smartcosmos.client.common.registration;

import net.smartcosmos.client.connectivity.ServiceException;

/**
 * Public registration operations, including a realm availability check and new account creation.
 */
public interface IRegistrationClient
{
    /**
     * Determines if the specified realm is available for registration.
     *
     * @param realm Realm to check availability
     * @return true, if the realm is available for registration, or false if the realm has already been registered by
     * someone else
     * @throws ServiceException If the realm check failed for any reason
     */
    boolean isRealmAvailable(String realm) throws ServiceException;

    /**
     * Attempts to register a new account under the specified email address where the realm is dynamically extracted
     * from the domain name of the email address.
     *
     * @param emailAddress Email address to register the account under, where the domain name is dynamically
     *                     extracted to be the realm name
     * @return Registration response containing the account URN
     * @throws ServiceException If the registration failed for any reason
     */
    RegistrationResponse register(String emailAddress) throws ServiceException;

    /**
     * Attempts to register a new account under the specified email address using the explicitly provided realm.
     *
     * @param emailAddress Email address to register the account under
     * @param realm        Name of the realm
     * @return Registration response containing the account URN
     * @throws ServiceException If the registration failed for any reason
     */
    RegistrationResponse register(String emailAddress, String realm) throws ServiceException;
}
