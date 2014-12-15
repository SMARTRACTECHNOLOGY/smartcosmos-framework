package net.smartcosmos.model.batch;

public interface IBatchProcessorReport extends ITransmissionUrnNamespace
{
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
}
