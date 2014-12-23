

package net.smartcosmos.util.mapper;

import com.google.common.base.Preconditions;

public class BooleanMapper implements IMetadataValueMapper<Boolean>
{
    @Override
    public byte[] toBytes(Boolean value)
    {
        if (value)
        {
            return new byte[]{0x01};
        } else
        {
            return new byte[]{0x00};
        }
    }

    @Override
    public Boolean fromBytes(byte[] rawValue)
    {
        Preconditions.checkArgument(((null != rawValue) && rawValue.length > 0), "rawValue must be non-null with a lenhth of 1");
        return (rawValue[0] == 0x01);
    }
}
