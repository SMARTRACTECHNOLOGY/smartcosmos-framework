package net.smartcosmos.client.batch;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.command.PostCommand;
import net.smartcosmos.client.impl.command.PutCommand;
import net.smartcosmos.client.impl.endpoint.BatchEndpoints;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.pojo.batch.BatchTransmissionResponse;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

public class BatchUploadClient implements IBatchUploadClient
{
    private final ServerContext context;

    public BatchUploadClient(ServerContext context)
    {
        this.context = context;
    }

    public IBatchTransmissionResponse sendBatchTransmissionRequest(IBatchTransmissionRequest request) throws ServiceException
    {
        Preconditions.checkNotNull(request);

        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(request, ViewType.Full));

            // PUT
            PutCommand<BatchTransmissionResponse> command = new PutCommand<>(context);
            return command.call(BatchTransmissionResponse.class, BatchEndpoints.fileTransmissionRequest(), json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    public void sendBatchTransmissionReceipt(IBatchTransmissionReceipt receipt) throws ServiceException
    {
        Preconditions.checkNotNull(receipt);

        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(receipt, ViewType.Full));

            // POST
            PostCommand command = new PostCommand(context);
            command.call(Object.class, BatchEndpoints.fileTransmissionReceipt(), json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }
}
