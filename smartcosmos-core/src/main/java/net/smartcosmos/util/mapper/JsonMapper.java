package net.smartcosmos.util.mapper;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonMapper implements IMetadataValueMapper<JSONObject>
{
    private final StringMapper stringMapper = new StringMapper();

    @Override
    public byte[] toBytes(JSONObject value)
    {
        byte[] result = new byte[0];

        try
        {
            result = stringMapper.toBytes(value.toString(3));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JSONObject fromBytes(byte[] rawValue)
    {
        JSONObject jsonObject = new JSONObject();

        String json = stringMapper.fromBytes(rawValue);
        try
        {
            jsonObject = new JSONObject(json);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return jsonObject;
    }
}