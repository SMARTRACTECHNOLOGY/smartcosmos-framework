package net.smartcosmos.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;

/**
 * System user with login and query privileges. Users are typically
 * not considered part of the contextual model captured by the system.
 * <p/>
 * SMART COSMOS Objects is <i>not a directory services</i> platform, so the constituent
 * parts of a user are relatively simplistic. Directory services are instead
 * a delegated operation based on the configuration of the platform as a whole.
 */
public interface IUser extends IAccountDomainResource<IUser>
{
    String getGivenName();

    void setGivenName(String givenName);

    String getSurname();

    void setSurname(String surname);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    RoleType getRoleType();

    void setRoleType(RoleType role);

    void copy(IUser user);
}
