package net.smartcosmos.client.batch;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;

public interface IBatchUploadClient
{
    IBatchTransmissionResponse sendBatchTransmissionRequest(IBatchTransmissionRequest request) throws ServiceException;

    void sendBatchTransmissionReceipt(IBatchTransmissionReceipt receipt) throws ServiceException;


}
