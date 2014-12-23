package net.smartcosmos.pojo.context;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.util.mapper.BooleanMapper;
import net.smartcosmos.util.mapper.DateMapper;
import net.smartcosmos.util.mapper.DoubleMapper;
import net.smartcosmos.util.mapper.FloatMapper;
import net.smartcosmos.util.mapper.IMetadataValueMapper;
import net.smartcosmos.util.mapper.IntegerMapper;
import net.smartcosmos.util.mapper.JsonMapper;
import net.smartcosmos.util.mapper.LongMapper;
import net.smartcosmos.util.mapper.NoopMapper;
import net.smartcosmos.util.mapper.StringMapper;

public class TypeSafeMetadata<T> implements IMetadata
{
    private final IMetadata metadataObject;

    private IMetadataValueMapper<T> mapper;

    public TypeSafeMetadata(IMetadata metadataObject)
    {
        this.metadataObject = metadataObject;

        switch (metadataObject.getDataType())
        {
            case Custom:
                mapper = (IMetadataValueMapper<T>) new NoopMapper();
                break;
            case StringType:
                mapper = (IMetadataValueMapper<T>) new StringMapper();
                break;
            case DateType:
                mapper = (IMetadataValueMapper<T>) new DateMapper();
                break;
            case JSONType:
                mapper = (IMetadataValueMapper<T>) new JsonMapper();
                break;
            case XMLType:
                mapper = (IMetadataValueMapper<T>) new StringMapper();
                break;
            case IntegerType:
                mapper = (IMetadataValueMapper<T>) new IntegerMapper();
                break;
            case LongType:
                mapper = (IMetadataValueMapper<T>) new LongMapper();
                break;
            case FloatType:
                mapper = (IMetadataValueMapper<T>) new FloatMapper();
                break;
            case DoubleType:
                mapper = (IMetadataValueMapper<T>) new DoubleMapper();
                break;
            case BooleanType:
                mapper = (IMetadataValueMapper<T>) new BooleanMapper();
                break;
        }
    }

    @Override
    public IAccount getAccount()
    {
        return metadataObject.getAccount();
    }

    @Override
    public void setAccount(IAccount account)
    {
        metadataObject.setAccount(account);
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return metadataObject.getEntityReferenceType();
    }

    @Override
    public void setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        metadataObject.setEntityReferenceType(entityReferenceType);
    }

    @Override
    public String getReferenceUrn()
    {
        return metadataObject.getReferenceUrn();
    }

    @Override
    public void setReferenceUrn(String urn)
    {
        metadataObject.setReferenceUrn(urn);
    }

    @Override
    public MetadataDataType getDataType()
    {
        return metadataObject.getDataType();
    }

    @Override
    public void setDataType(MetadataDataType type)
    {
        throw new UnsupportedOperationException("Type safe metadata object cannot reassign metadata data type");
    }

    @Override
    public String getKey()
    {
        return metadataObject.getKey();
    }

    @Override
    public void setKey(String key)
    {
        metadataObject.setKey(key);
    }

    @Override
    public byte[] getRawValue()
    {
        return metadataObject.getRawValue();
    }

    public T getValue()
    {
        return mapper.fromBytes(metadataObject.getRawValue());
    }

    public void setValue(T value)
    {
        metadataObject.setRawValue(mapper.toBytes(value));
    }

    @Override
    public void setRawValue(byte[] value)
    {
        metadataObject.setRawValue(value);
    }

    @Override
    public long getUniqueId()
    {
        return metadataObject.getUniqueId();
    }

    @Override
    public void setUniqueId(long uniqueId)
    {
        metadataObject.setUniqueId(uniqueId);
    }

    @Override
    public long getLastModifiedTimestamp()
    {
        return metadataObject.getLastModifiedTimestamp();
    }

    @Override
    public void copy(IMetadata object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copy operation");
    }

    @Override
    public String getMoniker()
    {
        return metadataObject.getMoniker();
    }

    @Override
    public void setMoniker(String moniker)
    {
        metadataObject.setMoniker(moniker);
    }

    @Override
    public String getUrn()
    {
        return metadataObject.getUrn();
    }

    @Override
    public void setUrn(String urn)
    {
        metadataObject.setUrn(urn);
    }
}
