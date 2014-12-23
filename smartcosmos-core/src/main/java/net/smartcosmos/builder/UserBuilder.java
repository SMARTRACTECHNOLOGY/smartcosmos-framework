package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.pojo.context.User;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.context.IUser} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#EMAIL_ADDRESS_FIELD}</li>
 * </ul>
 */
public final class UserBuilder extends AbstractMonikerBuilder<IUser, UserBuilder>
{
    public UserBuilder(String emailAddress)
    {
        super(new User());

        Preconditions.checkNotNull(emailAddress);
        instance.setEmailAddress(emailAddress);
        instance.setRoleType(RoleType.User);
    }

    public UserBuilder setGivenName(String givenName)
    {
        instance.setGivenName(givenName);
        return this;
    }

    public UserBuilder setSurname(String surname)
    {
        instance.setSurname(surname);
        return this;
    }

    public UserBuilder setRoleType(RoleType roleType)
    {
        instance.setRoleType(roleType);
        return this;
    }
}
