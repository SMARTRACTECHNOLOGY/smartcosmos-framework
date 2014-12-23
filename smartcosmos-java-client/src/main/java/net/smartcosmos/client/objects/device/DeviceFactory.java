package net.smartcosmos.client.objects.device;

import net.smartcosmos.client.connectivity.ServerContext;

public final class DeviceFactory
{
    private DeviceFactory()
    {
    }

    /**
     * Creates a new instance of a device client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IDeviceClient createClient(ServerContext context)
    {
        return new DeviceClient(context);
    }
}
