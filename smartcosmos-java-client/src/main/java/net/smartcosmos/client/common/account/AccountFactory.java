package net.smartcosmos.client.common.account;

import net.smartcosmos.client.connectivity.ServerContext;

public final class AccountFactory
{
    /**
     * Creates a new instance of a registration client.
     *
     * @return New registration client instance
     */
    public static IAccountClient createClient(ServerContext context)
    {
        return new AccountClient(context);
    }
}
