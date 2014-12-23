package net.smartcosmos.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerMapper implements IMetadataValueMapper<Integer>
{
    @Override
    public byte[] toBytes(Integer value)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);

        try
        {
            dataOutputStream.writeInt(value);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    @Override
    public Integer fromBytes(byte[] rawValue)
    {
        Integer intValue = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(rawValue);
        DataInputStream dataInputStream = new DataInputStream(bais);

        try
        {
            intValue = dataInputStream.readInt();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return intValue;
    }
}
