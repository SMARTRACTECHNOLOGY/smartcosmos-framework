package net.smartcosmos.pojo.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

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
            default:
                throw new IllegalStateException("Unrecognized type");
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
    public String getUniqueId()
    {
        return metadataObject.getUniqueId();
    }

    @Override
    public void setUniqueId(String uniqueId)
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
    public String getDecodedValue()
    {
        return metadataObject.getDecodedValue();
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
