package net.smartcosmos.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FloatMapper implements IMetadataValueMapper<Float>
{
    @Override
    public byte[] toBytes(Float value)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);

        try
        {
            dataOutputStream.writeFloat(value);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    @Override
    public Float fromBytes(byte[] rawValue)
    {
        Float floatValue = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(rawValue);
        DataInputStream dataInputStream = new DataInputStream(bais);

        try
        {
            floatValue = dataInputStream.readFloat();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return floatValue;
    }
}
