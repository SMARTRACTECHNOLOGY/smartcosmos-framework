package net.smartcosmos.model.batch;

import net.smartcosmos.model.base.IDomainResource;

public interface IBatchTransmission extends IDomainResource<IBatchTransmission>
{
    /**
     * Size (in bytes) of the file being transmitted
     *
     * @return file size, in bytes
     */
    long getFileContentLength();

    /**
     * Size (in bytes) of the file being transmitted
     *
     * @param fileContentLength file size, in bytes
     */
    void setFileContentLength(long fileContentLength);

    /**
     * Base64 encoded, 128-bit MD5 digest of the file per RFC1864.
     *
     * @return Base64 encoded 128-bit MD5 digest of the file being transmitted
     */
    String getFileMd5Checksum();

    /**
     * Base64 encoded, 128-bit MD5 digest of the file per RFC1864.
     *
     * @param md5Checksum Base64 encoded 128-bit MD5 digest of the file being transmitted
     */
    void setFileMd5Checksum(String md5Checksum);

    /**
     * MIME type describing the format of the file being uploaded, e.g. {@code text/xml}.
     *
     * @return standard MIME type of the file being uploaded
     */
    String getFileContentType();

    /**
     * MIME type describing the format of the file being uploaded, e.g. {@code text/xml}.
     *
     * @param contentType standard MIME type of the file being uploaded
     */
    void setFileContentType(String contentType);

    /**
     * Identifies an internal, previously configured, routing URN within the server. Routing URNs identify a controller
     * class that has <i>a priori</i> knowledge of how to process and handle the data contained within the file being
     * uploaded.
     *
     * @return Previously configured routing URN
     */
    String getRoutingUrn();

    /**
     * Identifies an internal, previously configured, routing URN within the server. Routing URNs identify a controller
     * class that has <i>a priori</i> knowledge of how to process and handle the data contained within the file being
     * uploaded.
     *
     * @param routingUrn Previously configured routing URN
     */
    void setRoutingUrn(String routingUrn);

    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);

    /**
     * Identifies the endpoint <i>where</i> the caller should transmit the actual file content.
     *
     * @return Endpoint where the actual file should be sent
     */
    String getEndpointUri();

    void setEndpointUri(String endpointUri);

    long getBatchProcessorStartTimestamp();

    void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp);

    BatchProcessorStatus getBatchProcessorStatus();

    void setBatchProcessorStatus(BatchProcessorStatus batchProcessorStatus);

    int getPercentageComplete();

    void setPercentageComplete(int percentageComplete);

    long getLastPercentageCompleteUpdateTimestamp();

    void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp);

    int getErrorCode();

    void setErrorCode(int errorCode);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);

    long getBatchProcessorStopTimestamp();

    void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp);

    TransmissionResultType getTransmissionResult();

    void setTransmissionResult(TransmissionResultType transmissionResult);
}
