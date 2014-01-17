package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.base.ITypeObject;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonGenerationView;

public abstract class TypeObject<T> extends NamedObject<T> implements ITypeObject<T>
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
    public void copy(T type)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof TypeObject)) return false;
        if (!super.equals(o)) return false;

        TypeObject that = (TypeObject) o;

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
