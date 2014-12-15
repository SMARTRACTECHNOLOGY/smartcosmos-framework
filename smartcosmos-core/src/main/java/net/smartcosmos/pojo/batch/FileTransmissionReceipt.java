package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IFileTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.util.json.JsonGenerationView;

public class FileTransmissionReceipt extends TransmissionUrnNamespace implements IFileTransmissionReceipt
{
    @JsonView(JsonGenerationView.Minimum.class)
    private TransmissionResultType transmissionResult;

    @Override
    public TransmissionResultType getTransmissionResult()
    {
        return transmissionResult;
    }

    @Override
    public void setTransmissionResult(TransmissionResultType transmissionResult)
    {
        this.transmissionResult = transmissionResult;
    }
}
