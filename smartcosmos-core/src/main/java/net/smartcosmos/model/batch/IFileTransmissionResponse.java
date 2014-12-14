package net.smartcosmos.model.batch;

public interface IFileTransmissionResponse extends ITransmissionUrnNamespace
{
    /**
     * Identifies the endpoint <i>where</i> the caller should transmit the actual file content.
     *
     * @return Endpoint where the actual file should be sent
     */
    String getEndpointUri();

    void setEndpointUri(String endpointUri);
}
