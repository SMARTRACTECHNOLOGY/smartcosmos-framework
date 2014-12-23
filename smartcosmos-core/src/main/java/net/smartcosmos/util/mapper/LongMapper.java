package net.smartcosmos.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LongMapper implements IMetadataValueMapper<Long>
{
    @Override
    public byte[] toBytes(Long value)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);

        try
        {
            dataOutputStream.writeLong(value);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    @Override
    public Long fromBytes(byte[] rawValue)
    {
        Long longVal = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(rawValue);
        DataInputStream dataInputStream = new DataInputStream(bais);
        try
        {
            longVal = dataInputStream.readLong();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return longVal;
    }
}
