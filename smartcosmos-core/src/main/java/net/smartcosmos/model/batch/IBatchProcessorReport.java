package net.smartcosmos.model.batch;

public interface IBatchProcessorReport extends ITransmissionUrnNamespace
{
    long getBatchProcessorStartTimestamp();

    void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp);

    boolean hasProcessingStarted();

    void setProcessingStarted(boolean flag);

    int getPercentageComplete();

    void setPercentageComplete(int percentageComplete);

    long getLastPercentageCompleteUpdateTimestamp();

    void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp);

    boolean isProcessingComplete();

    void setProcessingComplete(boolean flag);

    boolean wasProcessingSuccessful();

    void setProcessingSuccessful(boolean flag);

    int getErrorCode();

    void setErrorCode(int errorCode);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);

    long getBatchProcessorStopTimestamp();

    void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp);
}
