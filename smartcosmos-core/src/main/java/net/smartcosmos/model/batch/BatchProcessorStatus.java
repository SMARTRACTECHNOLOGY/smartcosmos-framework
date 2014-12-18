package net.smartcosmos.model.batch;

public enum BatchProcessorStatus
{
    AwaitingFileTransmissionReceipt,
    AbortedByCaller,
    TransmissionErrorAwaitingRetry,
    QueuedForProcessing,
    StartProcessing,
    PercentageCompleteUpdate,
    ErrorProcessing,
    EndProcessing
}
