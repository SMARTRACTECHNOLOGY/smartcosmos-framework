package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Identifies a specific type of computation device and a means of uniquely
 * identifying a specific device. Devices are things like smartphones, laptops,
 * or custom hardware. The {@link #getIdentification()} in the case of a smartphone
 * might be the manufacturer's IMEI or the carrier-assigned phone number. For a
 * laptop the identification might be the MAC address of the NIC. In extreme cases,
 * the developer may choose to arbitrarily assign sequential numbers to uniquely
 * identify the various devices managed by the system.
 */
public interface IDevice extends IAccountDomainResource<IDevice>, INamedObject<IDevice>, ITypedObject
{
    String getIdentification();

    void setIdentification(String identification);
}
