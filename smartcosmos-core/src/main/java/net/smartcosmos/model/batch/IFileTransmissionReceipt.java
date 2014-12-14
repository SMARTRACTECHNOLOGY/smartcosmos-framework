package net.smartcosmos.model.batch;

public interface IFileTransmissionReceipt extends ITransmissionUrnNamespace
{
    TransmissionResult getTransmissionResult();

    void setTransmissionResult(TransmissionResult transmissionResult);
}
