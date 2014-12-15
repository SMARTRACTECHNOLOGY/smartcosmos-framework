package net.smartcosmos.model.batch;

public interface IFileTransmissionReceipt extends ITransmissionUrnNamespace
{
    TransmissionResultType getTransmissionResult();

    void setTransmissionResult(TransmissionResultType transmissionResult);
}
