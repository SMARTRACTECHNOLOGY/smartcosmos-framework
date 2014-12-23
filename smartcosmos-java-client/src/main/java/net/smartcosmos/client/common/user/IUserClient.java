package net.smartcosmos.client.common.user;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.util.json.ViewType;

/**
 * Defines, updates or queries for {@link net.smartcosmos.model.context.IUser} instances.
 */
public interface IUserClient extends IUpdateableBaseClient<IUser>
{
    /**
     * Locates the user record associated with the specified email address using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param emailAddress Email address of the user to locate
     * @return User record
     * @throws ServiceException if the email address does not exist
     */
    IUser findByEmailAddress(String emailAddress) throws ServiceException;

    /**
     * Locates the user record associated with the specified email address using the specified field verbosity.
     *
     * @param emailAddress Email address of the user to locate
     * @param viewType     Field verbosity
     * @return User record
     * @throws ServiceException if the email address does not exist
     */
    IUser findByEmailAddress(String emailAddress, ViewType viewType) throws ServiceException;

    /**
     * Assigns a new password to the user associated with the specified email address <b>so long as the
     * call is assigned the {@link net.smartcosmos.model.context.RoleType#Administrator}</b> role.
     *
     * @param emailAddress Email address of the user to locate
     * @param newPassword  New password to assign
     * @throws ServiceException
     */
    void changePassword(String emailAddress, String newPassword) throws ServiceException;

    /**
     * Initiates a password reset workflow for the user associated with the specified email address <b>so long as the
     * call is assigned the {@link net.smartcosmos.model.context.RoleType#Administrator}</b> role.
     *
     * @param emailAddress Email address of the user to initiate a password reset workflow on
     * @throws ServiceException
     */
    void resetPassword(String emailAddress) throws ServiceException;
}
