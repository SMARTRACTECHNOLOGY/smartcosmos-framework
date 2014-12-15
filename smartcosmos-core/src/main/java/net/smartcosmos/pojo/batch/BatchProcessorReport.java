package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IBatchProcessorReport;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchProcessorReport extends TransmissionUrnNamespace implements IBatchProcessorReport
{
    @JsonView(JsonGenerationView.Minimum.class)
    private long batchProcessorStartTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private boolean processingStartedFlag;

    @JsonView(JsonGenerationView.Minimum.class)
    private int percentageComplete;

    @JsonView(JsonGenerationView.Minimum.class)
    private long lastPercentageCompleteUpdateTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private boolean processingCompleteFlag;

    @JsonView(JsonGenerationView.Minimum.class)
    private boolean processingSuccessfulFlag;

    @JsonView(JsonGenerationView.Minimum.class)
    private int errorCode;

    @JsonView(JsonGenerationView.Minimum.class)
    private String errorMessage;

    @JsonView(JsonGenerationView.Minimum.class)
    private long batchProcessorStopTimestamp;

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
    public boolean hasProcessingStarted()
    {
        return processingStartedFlag;
    }

    @Override
    public void setProcessingStarted(boolean flag)
    {
        this.processingStartedFlag = flag;
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
    public boolean isProcessingComplete()
    {
        return processingCompleteFlag;
    }

    @Override
    public void setProcessingComplete(boolean flag)
    {
        this.processingCompleteFlag = flag;
    }

    @Override
    public boolean wasProcessingSuccessful()
    {
        return processingSuccessfulFlag;
    }

    @Override
    public void setProcessingSuccessful(boolean flag)
    {
        this.processingSuccessfulFlag = flag;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchProcessorReport that = (BatchProcessorReport) o;

        if (batchProcessorStartTimestamp != that.batchProcessorStartTimestamp) return false;
        if (batchProcessorStopTimestamp != that.batchProcessorStopTimestamp) return false;
        if (errorCode != that.errorCode) return false;
        if (lastPercentageCompleteUpdateTimestamp != that.lastPercentageCompleteUpdateTimestamp) return false;
        if (percentageComplete != that.percentageComplete) return false;
        if (processingCompleteFlag != that.processingCompleteFlag) return false;
        if (processingStartedFlag != that.processingStartedFlag) return false;
        if (processingSuccessfulFlag != that.processingSuccessfulFlag) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (batchProcessorStartTimestamp ^ (batchProcessorStartTimestamp >>> 32));
        result = 31 * result + (processingStartedFlag ? 1 : 0);
        result = 31 * result + percentageComplete;
        result = 31 * result + (int) (lastPercentageCompleteUpdateTimestamp ^ (lastPercentageCompleteUpdateTimestamp >>> 32));
        result = 31 * result + (processingCompleteFlag ? 1 : 0);
        result = 31 * result + (processingSuccessfulFlag ? 1 : 0);
        result = 31 * result + errorCode;
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (int) (batchProcessorStopTimestamp ^ (batchProcessorStopTimestamp >>> 32));
        return result;
    }
}
