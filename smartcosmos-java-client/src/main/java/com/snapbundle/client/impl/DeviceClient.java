package com.snapbundle.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snapbundle.Constant;
import com.snapbundle.client.CreationException;
import com.snapbundle.client.IDeviceClient;
import com.snapbundle.client.SnapEndpoint;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.util.JsonGenerationView;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class DeviceClient extends AbstractClient implements IDeviceClient
{
    @Override
    public String create(JSONObject jsonObject) throws CreationException
    {
        String urn;
        ClientResource service = createClient(SnapEndpoint.devices(), true);

        JSONObject json;
        try
        {
            json = toJsonObject(service.put(new JsonRepresentation(jsonObject)));

            if (service.getStatus() != Status.SUCCESS_CREATED)
            {
                throw new CreationException("Status was not " + Status.SUCCESS_CREATED);
            }

            urn = json.getString(Constant.MESSAGE_FIELD);

        } catch (JSONException | IOException e)
        {
            e.printStackTrace();
            throw new CreationException(e);
        }

        return urn;
    }

    @Override
    public String create(IDevice device) throws CreationException
    {
        try
        {
            return create(new JSONObject(device.toJson(JsonGenerationView.Full.class)));
        } catch (JsonProcessingException | JSONException e)
        {
            e.printStackTrace();
            throw new CreationException(e);
        }
    }


}
