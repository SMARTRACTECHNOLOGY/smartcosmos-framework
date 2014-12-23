

package net.smartcosmos.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class User extends DomainResource<IUser> implements IUser
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String emailAddress;

    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Full.class)
    private String givenName;

    @JsonView(JsonGenerationView.Full.class)
    private String surname;

    @JsonView(JsonGenerationView.Minimum.class)
    protected RoleType roleType;

    @Override
    public String getEmailAddress()
    {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public String getGivenName()
    {
        return givenName;
    }

    @Override
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    @Override
    public String getSurname()
    {
        return surname;
    }

    @Override
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    @Override
    public RoleType getRoleType()
    {
        return roleType;
    }

    @Override
    public void setRoleType(RoleType roleType)
    {
        this.roleType = roleType;
    }

    @Override
    public void copy(IUser target)
    {
        this.urn = target.getUrn();
        this.uniqueId = target.getUniqueId();
        this.lastModifiedTimestamp = target.getLastModifiedTimestamp();
        this.moniker = target.getMoniker();

        this.emailAddress = target.getEmailAddress();
        this.account = target.getAccount();

        this.givenName = target.getGivenName();
        this.surname = target.getSurname();

        this.roleType = target.getRoleType();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!account.equals(user.account)) return false;
        if (!emailAddress.equals(user.emailAddress)) return false;
        if (givenName != null ? !givenName.equals(user.givenName) : user.givenName != null) return false;
        if (roleType != user.roleType) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + roleType.hashCode();
        return result;
    }
}
