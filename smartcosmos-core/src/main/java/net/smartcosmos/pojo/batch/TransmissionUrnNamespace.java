package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.ITransmissionUrnNamespace;
import net.smartcosmos.util.json.JsonGenerationView;

public class TransmissionUrnNamespace implements ITransmissionUrnNamespace
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String transmissionUrn;

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
}
