

package net.smartcosmos.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleMapper implements IMetadataValueMapper<Double>
{
    @Override
    public byte[] toBytes(Double value)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);

        try
        {
            dataOutputStream.writeDouble(value);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    @Override
    public Double fromBytes(byte[] rawValue)
    {
        Double doubleValue = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(rawValue);
        DataInputStream dataInputStream = new DataInputStream(bais);

        try
        {
            doubleValue = dataInputStream.readDouble();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return doubleValue;
    }
}
