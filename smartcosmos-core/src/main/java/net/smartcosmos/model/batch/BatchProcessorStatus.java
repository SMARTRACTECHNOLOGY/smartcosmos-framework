package net.smartcosmos.model.batch;

public enum BatchProcessorStatus
{
    AwaitingFileTransmissionReceipt,
    QueuedForProcessing,
    StartProcessing,
    PercentageCompleteUpdate,
    ErrorProcessing,
    EndProcessing
}
