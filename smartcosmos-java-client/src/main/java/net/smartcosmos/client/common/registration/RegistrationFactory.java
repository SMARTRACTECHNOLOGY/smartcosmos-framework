package net.smartcosmos.client.common.registration;

import net.smartcosmos.client.connectivity.ServerContext;

/**
 * Registration Client Factory.
 */
public final class RegistrationFactory
{
    private RegistrationFactory()
    {
    }

    /**
     * Creates a new instance of a registration client.
     *
     * @param server Server location, e.g. https://objects.example.com
     * @return New registration client instance
     */
    public static IRegistrationClient createClient(String server)
    {
        return new RegistrationClient(server);
    }

    /**
     * Creates a new instance of a registration client pointing at a specific server instance.
     *
     * @return New registration client instance
     */
    public static IRegistrationClient createClient(ServerContext context)
    {
        return new RegistrationClient(context);
    }
}
