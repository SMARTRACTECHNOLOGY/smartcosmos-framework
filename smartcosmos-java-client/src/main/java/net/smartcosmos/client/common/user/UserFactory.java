package net.smartcosmos.client.common.user;

import net.smartcosmos.client.connectivity.ServerContext;

public final class UserFactory
{
    private UserFactory()
    {
    }

    /**
     * Creates a new instance of a user client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IUserClient createClient(ServerContext context)
    {
        return new UserClient(context);
    }
}
