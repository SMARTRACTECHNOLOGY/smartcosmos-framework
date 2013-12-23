/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.pojo.context;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IMetadata;
import com.snapbundle.model.context.MetadataDataType;
import com.snapbundle.util.mapper.BooleanMapper;
import com.snapbundle.util.mapper.DateMapper;
import com.snapbundle.util.mapper.DoubleMapper;
import com.snapbundle.util.mapper.FloatMapper;
import com.snapbundle.util.mapper.IMetadataValueMapper;
import com.snapbundle.util.mapper.IntegerMapper;
import com.snapbundle.util.mapper.JsonMapper;
import com.snapbundle.util.mapper.LongMapper;
import com.snapbundle.util.mapper.NoopMapper;
import com.snapbundle.util.mapper.StringMapper;

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
