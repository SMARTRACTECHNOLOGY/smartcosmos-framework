package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.context.Metadata;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.context.IMetadata} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#KEY_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#RAW_VALUE_FIELD}</li>
 * </ul>
 */
public final class MetadataBuilder extends AbstractReferentialBuilder<IMetadata, MetadataBuilder>
{
    public MetadataBuilder(MetadataDataType dataType)
    {
        super(new Metadata());
        instance.setDataType(dataType);
    }

    public MetadataBuilder setKey(String key)
    {
        instance.setKey(key);
        return this;
    }

    public MetadataBuilder setRawValue(byte[] rawValue)
    {
        instance.setRawValue(rawValue);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getKey(), "key must not be null");
        Preconditions.checkNotNull(instance.getRawValue(), "raw value must not be null");
    }
}
