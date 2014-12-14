package net.smartcosmos.model.batch;

public interface ITransmissionUrnNamespace
{
    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);
}
