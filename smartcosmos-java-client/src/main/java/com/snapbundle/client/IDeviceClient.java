package com.snapbundle.client;

import com.snapbundle.model.context.IDevice;
import org.json.JSONObject;

public interface IDeviceClient
{
    String create(JSONObject jsonObject) throws CreationException;

    String create(IDevice device) throws CreationException;
}
