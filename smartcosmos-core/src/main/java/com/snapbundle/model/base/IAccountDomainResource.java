package com.snapbundle.model.base;

import com.snapbundle.model.context.IAccount;

public interface IAccountDomainResource<T> extends IDomainResource<T>
{
    IAccount getAccount();

    void setAccount(IAccount account);
}
