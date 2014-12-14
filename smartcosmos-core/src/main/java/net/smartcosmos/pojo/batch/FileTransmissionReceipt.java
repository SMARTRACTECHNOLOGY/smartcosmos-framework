package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IFileTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResult;
import net.smartcosmos.util.json.JsonGenerationView;

public class FileTransmissionReceipt extends TransmissionUrnNamespace implements IFileTransmissionReceipt
{
    @JsonView(JsonGenerationView.Minimum.class)
    private TransmissionResult transmissionResult;

    @Override
    public TransmissionResult getTransmissionResult()
    {
        return transmissionResult;
    }

    @Override
    public void setTransmissionResult(TransmissionResult transmissionResult)
    {
        this.transmissionResult = transmissionResult;
    }
}
