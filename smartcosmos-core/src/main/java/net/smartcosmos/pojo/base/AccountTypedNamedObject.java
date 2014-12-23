package net.smartcosmos.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.base.IAccountContext;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;

public class AccountTypedNamedObject<T> extends TypedNamedObject<T> implements IAccountContext
{
    @JsonDeserialize(as = Account.class)
    @JsonView(JsonGenerationView.Full.class)
    protected IAccount account;

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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AccountTypedNamedObject that = (AccountTypedNamedObject) o;

        if (!account.equals(that.account)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + account.hashCode();
        return result;
    }
}
