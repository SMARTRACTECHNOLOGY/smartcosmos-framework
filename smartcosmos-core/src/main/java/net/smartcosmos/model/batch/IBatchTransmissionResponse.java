package net.smartcosmos.model.batch;

import net.smartcosmos.model.base.IDomainResource;

public interface IBatchTransmissionResponse extends IDomainResource<IBatchTransmissionResponse>
{
    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);

    /**
     * Identifies the endpoint <i>where</i> the caller should transmit the actual file content.
     *
     * @return Endpoint where the actual file should be sent
     */
    String getEndpointUri();

    void setEndpointUri(String endpointUri);

    String getProtocol();

    void setProtocol(String protocol);
}
