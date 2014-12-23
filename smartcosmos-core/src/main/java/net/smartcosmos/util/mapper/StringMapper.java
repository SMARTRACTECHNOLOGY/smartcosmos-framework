package net.smartcosmos.util.mapper;

import java.nio.charset.Charset;

public final class StringMapper implements IMetadataValueMapper<String>
{
    @Override
    public byte[] toBytes(String value)
    {
        return value.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public String fromBytes(byte[] rawValue)
    {
        return new String(rawValue, 0, rawValue.length, Charset.forName("UTF-8"));
    }
}
