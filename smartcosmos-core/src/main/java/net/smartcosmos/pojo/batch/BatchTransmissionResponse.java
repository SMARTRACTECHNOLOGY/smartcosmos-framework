package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmissionResponse extends DomainResource<IBatchTransmissionResponse> implements IBatchTransmissionResponse
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String transmissionUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private String endpointUri;

    @Override
    public String getTransmissionUrn()
    {
        return transmissionUrn;
    }

    @Override
    public void setTransmissionUrn(String transmissionUrn)
    {
        this.transmissionUrn = transmissionUrn;
    }

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
