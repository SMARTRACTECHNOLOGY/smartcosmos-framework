package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IFileTransmissionResponse;
import net.smartcosmos.util.json.JsonGenerationView;

public class FileTransmissionResponse extends TransmissionUrnNamespace implements IFileTransmissionResponse
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String endpointUri;

    @Override
    public String getEndpointUri()
    {
        return endpointUri;
    }

    @Override
    public void setEndpointUri(String endpointUri)
    {
        this.endpointUri = endpointUri;
    }
}
