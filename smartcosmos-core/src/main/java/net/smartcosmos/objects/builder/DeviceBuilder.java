package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IDevice;
import net.smartcosmos.objects.pojo.context.Device;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IDevice} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#DEVICE_IDENTIFICATION_FIELD}</li>
 * </ul>
 */
public final class DeviceBuilder extends AbstractNamedObjectBuilder<IDevice, DeviceBuilder>
{
    public DeviceBuilder(String identification)
    {
        super(new Device());

        Preconditions.checkNotNull(identification);
        instance.setIdentification(identification);
    }

    public DeviceBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public DeviceBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }

}
