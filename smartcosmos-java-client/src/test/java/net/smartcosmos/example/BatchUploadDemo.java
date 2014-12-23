package net.smartcosmos.example;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
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
import org.apache.shiro.codec.Base64;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BatchUploadDemo
{

    private static final String SERVER_URL = "http://localhost:8080";

    public static final String TEXT_PLAIN = "text/plain";

    public static final String SAMPLE_FILE_CONTENT = "sample";

    public static void main(String[] args) throws ServiceException
    {
        BatchUploadDemo demo = new BatchUploadDemo();
        demo.run();
    }

    private void run() throws ServiceException
    {
        //
        // Establish a connection to the Objects server
        //
        ServerContext context = new ServerContext(SERVER_URL);
        IBatchUploadClient client = BatchUploadFactory.createClient(context);

        //
        // Calculate an MD5 hash of the file's content being uploaded
        // NOTE: The MD5 hash must be Base64 encoded!
        //
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putString(SAMPLE_FILE_CONTENT, Charsets.UTF_8)
                .hash();

        //
        // Create a Batch Transmission Request, indicating the assigned Routing URN,
        // the precise length of the content being upload, the Base64 encoded MD5 hash
        // of the file's contents, and the MIME type of the file being sent.
        //
        // NOTE: If these values aren't precise, the server-calculated signature on
        //       upload will not match and a 403 will be sent back with an error message
        //       from the AWS S3 service!
        //
        IBatchTransmissionRequest request = new BatchTransmissionRequestBuilder("urn:uuid:12345")
                .setFileContentLength(SAMPLE_FILE_CONTENT.length())
                .setFileMd5Checksum(Base64.encodeToString(hc.asBytes()))
                .setFileContentType(TEXT_PLAIN)
                .build();

        //
        // Send the batch transmission request over to Objects
        //
        IBatchTransmissionResponse response = client.sendBatchTransmissionRequest(request);

        //
        // The Batch Transmission Response's Transmission URN is your key for all future
        // interactions with this batch file, including both the actual transmission receipt
        // used to indicate the upload completed successfully (or was aborted, errored, etc.)
        // and for all future reporting queries to see if the batch processing client
        // successfully processed the batch file on the server-side and the data is now available
        // in the Objects server.
        //
        System.out.println(response.getTransmissionUrn());
        System.out.println(response.getEndpointUri());

        //
        // At this point, actually transmit the data with a PUT to the pre-signed URL endpoint
        //
        // NOTE: The Content-Type, the Content-MD5, and the file's content must be exactly
        //       what was signed or a 4xx HTTP status code will be returned from AWS S3!
        //
        try
        {
            URL url = new URL(response.getEndpointUri());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", TEXT_PLAIN);
            connection.setRequestProperty("Content-MD5", Base64.encodeToString(hc.asBytes()));
            connection.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(SAMPLE_FILE_CONTENT);
            out.close();
            int responseCode = connection.getResponseCode();
            System.out.println("Service returned response code " + responseCode);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //
        // Finally, you must notify the Objects server that you've successfully completed the file
        // upload and that the file is now available for processing by the async client polling for
        // work on the server side.
        //
        IBatchTransmissionReceipt receipt = new BatchTransmissionReceiptBuilder(response.getTransmissionUrn())
                .setTransmissionResult(TransmissionResultType.UploadComplete)
                .build();

        client.sendBatchTransmissionReceipt(receipt);
    }
}
