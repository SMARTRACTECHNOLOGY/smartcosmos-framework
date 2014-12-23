package net.smartcosmos.model.base;

import net.smartcosmos.model.context.IAccount;

/**
 * Establishes an account context that formally owns the domain resource.
 */
public interface IAccountContext
{
    IAccount getAccount();

    void setAccount(IAccount account);
}
