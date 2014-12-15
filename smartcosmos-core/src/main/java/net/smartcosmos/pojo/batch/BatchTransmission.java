package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.BatchProcessorStatus;
import net.smartcosmos.model.batch.IBatchTransmission;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmission extends DomainResource<IBatchTransmission> implements IBatchTransmission
{
    @JsonView(JsonGenerationView.Minimum.class)
    private long batchProcessorStartTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private int percentageComplete;

    @JsonView(JsonGenerationView.Minimum.class)
    private long lastPercentageCompleteUpdateTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private BatchProcessorStatus batchProcessorStatus;

    @JsonView(JsonGenerationView.Minimum.class)
    private int errorCode;

    @JsonView(JsonGenerationView.Minimum.class)
    private String errorMessage;

    @JsonView(JsonGenerationView.Minimum.class)
    private long batchProcessorStopTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private String transmissionUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private String endpointUri;

    @JsonView(JsonGenerationView.Minimum.class)
    private String md5Checksum;

    @JsonView(JsonGenerationView.Minimum.class)
    private String contentType;

    @JsonView(JsonGenerationView.Minimum.class)
    private String routingUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private long contentLength;

    @JsonView(JsonGenerationView.Minimum.class)
    private TransmissionResultType transmissionResult;

    @Override
    public long getFileContentLength()
    {
        return contentLength;
    }

    @Override
    public void setFileContentLength(long contentLength)
    {
        this.contentLength = contentLength;
    }

    @Override
    public String getFileMd5Checksum()
    {
        return md5Checksum;
    }

    @Override
    public void setFileMd5Checksum(String md5Checksum)
    {
        this.md5Checksum = md5Checksum;
    }

    @Override
    public String getFileContentType()
    {
        return contentType;
    }

    @Override
    public void setFileContentType(String contentType)
    {
        this.contentType = contentType;
    }

    @Override
    public String getRoutingUrn()
    {
        return routingUrn;
    }

    @Override
    public void setRoutingUrn(String routingUrn)
    {
        this.routingUrn = routingUrn;
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
    public long getBatchProcessorStartTimestamp()
    {
        return batchProcessorStartTimestamp;
    }

    @Override
    public void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp)
    {
        this.batchProcessorStartTimestamp = batchProcessorStartTimestamp;
    }

    @Override
    public int getPercentageComplete()
    {
        return percentageComplete;
    }

    @Override
    public void setPercentageComplete(int percentageComplete)
    {
        this.percentageComplete = percentageComplete;
    }

    @Override
    public long getLastPercentageCompleteUpdateTimestamp()
    {
        return lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp)
    {
        this.lastPercentageCompleteUpdateTimestamp = lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public long getBatchProcessorStopTimestamp()
    {
        return batchProcessorStopTimestamp;
    }

    @Override
    public void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp)
    {
        this.batchProcessorStopTimestamp = batchProcessorStopTimestamp;
    }

    @Override
    public BatchProcessorStatus getBatchProcessorStatus()
    {
        return batchProcessorStatus;
    }

    @Override
    public void setBatchProcessorStatus(BatchProcessorStatus batchProcessorStatus)
    {
        this.batchProcessorStatus = batchProcessorStatus;
    }
}
