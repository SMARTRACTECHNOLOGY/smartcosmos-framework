package com.snapbundle.model.base;

import com.snapbundle.model.context.IAccount;

public interface IAccountOwner
{
    IAccount getAccount();

    void setAccount(IAccount account);
}
