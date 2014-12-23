package net.smartcosmos.util.mapper;

public class NoopMapper implements IMetadataValueMapper<byte[]>
{
    @Override
    public byte[] toBytes(byte[] value)
    {
        return value;
    }

    @Override
    public byte[] fromBytes(byte[] rawValue)
    {
        return rawValue;
    }
}
