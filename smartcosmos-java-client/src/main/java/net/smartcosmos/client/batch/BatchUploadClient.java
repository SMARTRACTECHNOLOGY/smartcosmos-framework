package net.smartcosmos.client.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractBaseClient;
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

public class BatchUploadClient extends AbstractBaseClient implements IBatchUploadClient
{

    public BatchUploadClient(final ServerContext context)
    {
        super(context);
    }

    public IBatchTransmissionResponse sendBatchTransmissionRequest(final IBatchTransmissionRequest request)
            throws ServiceException
    {
        Preconditions.checkNotNull(request);

        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(request, ViewType.Full));

            // PUT
            PutCommand<BatchTransmissionResponse> command = new PutCommand<>(context, getClient());
            return command.call(BatchTransmissionResponse.class, BatchEndpoints.fileTransmissionRequest(), json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    public void sendBatchTransmissionReceipt(final IBatchTransmissionReceipt receipt) throws ServiceException
    {
        Preconditions.checkNotNull(receipt);

        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(receipt, ViewType.Full));

            // POST
            PostCommand command = new PostCommand(context, getClient());
            command.call(Object.class, BatchEndpoints.fileTransmissionReceipt(), json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }
}
