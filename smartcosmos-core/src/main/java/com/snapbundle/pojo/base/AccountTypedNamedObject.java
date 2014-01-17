package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.base.IAccountOwner;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonGenerationView;

public class AccountTypedNamedObject<T> extends TypedNamedObject<T> implements IAccountOwner
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
