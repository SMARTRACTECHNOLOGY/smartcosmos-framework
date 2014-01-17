package com.snapbundle.model.base;

import com.snapbundle.model.context.IAccount;

public interface ITypeObject<T> extends INamedObject<T>
{
    IAccount getAccount();

    void setAccount(IAccount account);
}
