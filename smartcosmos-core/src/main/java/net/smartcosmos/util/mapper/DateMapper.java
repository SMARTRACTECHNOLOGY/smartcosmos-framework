

package net.smartcosmos.util.mapper;

import java.util.Date;

public class DateMapper implements IMetadataValueMapper<Date>
{
    private final LongMapper longMapper = new LongMapper();

    @Override
    public byte[] toBytes(Date value)
    {
        return longMapper.toBytes(value.getTime());
    }

    @Override
    public Date fromBytes(byte[] rawValue)
    {
        return new Date(longMapper.fromBytes(rawValue));
    }
}
