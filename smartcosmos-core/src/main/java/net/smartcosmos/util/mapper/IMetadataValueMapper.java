package net.smartcosmos.util.mapper;

public interface IMetadataValueMapper<T>
{
    byte[] toBytes(T value);

    T fromBytes(byte[] rawValue);
}
