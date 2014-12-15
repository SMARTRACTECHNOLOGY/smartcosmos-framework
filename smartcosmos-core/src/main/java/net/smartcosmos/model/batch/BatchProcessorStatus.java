package net.smartcosmos.model.batch;

public enum BatchProcessorStatus
{
    QueuedForProcessing,
    StartProcessing,
    PercentageCompleteUpdate,
    ErrorProcessing,
    EndProcessing
}
