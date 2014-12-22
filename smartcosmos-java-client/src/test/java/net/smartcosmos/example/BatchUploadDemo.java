package net.smartcosmos.example;

import net.smartcosmos.builder.BatchTransmissionReceiptBuilder;
import net.smartcosmos.builder.BatchTransmissionRequestBuilder;
import net.smartcosmos.client.batch.BatchUploadFactory;
import net.smartcosmos.client.batch.IBatchUploadClient;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.model.batch.TransmissionResultType;

public class BatchUploadDemo
{
    private static final String SERVER_URL = "http://localhost:8080";

    public static void main(String[] args) throws ServiceException
    {
        BatchUploadDemo demo = new BatchUploadDemo();
        demo.run();
    }

    private void run() throws ServiceException
    {
        ServerContext context = new ServerContext(SERVER_URL);
        IBatchUploadClient client = BatchUploadFactory.createClient(context);

        IBatchTransmissionRequest request = new BatchTransmissionRequestBuilder("urn:uuid:12345")
                .setFileContentLength(123l)
                .setFileMd5Checksum("abcdef")
                .setFileContentType("text/xml")
                .build();

        IBatchTransmissionResponse response = client.sendBatchTransmissionRequest(request);

        System.out.println(response.getTransmissionUrn());
        System.out.println(response.getEndpointUri());

        //
        // At this point, developer should upload the FILE using a POST to the signed endpoint URL
        //

        //
        // Now, notify SMART COSMOS that the upload was completed successfully
        //
        IBatchTransmissionReceipt receipt = new BatchTransmissionReceiptBuilder(response.getTransmissionUrn())
                .setTransmissionResult(TransmissionResultType.UploadComplete)
                .build();

        client.sendBatchTransmissionReceipt(receipt);
    }
}
