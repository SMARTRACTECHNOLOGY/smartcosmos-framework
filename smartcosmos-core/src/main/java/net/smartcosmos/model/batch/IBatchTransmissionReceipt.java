package net.smartcosmos.model.batch;

import net.smartcosmos.model.base.IDomainResource;

public interface IBatchTransmissionReceipt extends IDomainResource<IBatchTransmissionReceipt>
{
    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);

    TransmissionResultType getTransmissionResult();

    void setTransmissionResult(TransmissionResultType transmissionResult);
}
