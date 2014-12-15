package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmissionReceipt extends DomainResource<IBatchTransmissionReceipt> implements IBatchTransmissionReceipt
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String transmissionUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private TransmissionResultType transmissionResult;

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

    public TransmissionResultType getTransmissionResult()
    {
        return transmissionResult;
    }

    public void setTransmissionResult(TransmissionResultType transmissionResult)
    {
        this.transmissionResult = transmissionResult;
    }
}
