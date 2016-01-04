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

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.base.ReferentialObject;
import net.smartcosmos.util.json.JsonGenerationView;
import net.smartcosmos.util.mapper.BooleanMapper;
import net.smartcosmos.util.mapper.DoubleMapper;
import net.smartcosmos.util.mapper.FloatMapper;
import net.smartcosmos.util.mapper.IntegerMapper;
import net.smartcosmos.util.mapper.LongMapper;
import net.smartcosmos.util.mapper.StringMapper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Metadata extends ReferentialObject<IMetadata> implements IMetadata
{
    public static class MetadataObjectBuilder
    {
        private MetadataDataType type;

        private String key;

        private String rawValue;

        private IAccount account;

        private EntityReferenceType entityReferenceType;

        private String referenceUrn;

        public MetadataObjectBuilder(MetadataDataType type)
        {
            this.type = type;
        }

        public MetadataObjectBuilder setAccount(IAccount account)
        {
            this.account = account;
            return this;
        }

        public MetadataObjectBuilder setEntityReferenceType(EntityReferenceType type)
        {
            this.entityReferenceType = type;
            return this;
        }

        public MetadataObjectBuilder setReferenceUrn(String urn)
        {
            this.referenceUrn = urn;
            return this;
        }

        public MetadataObjectBuilder setKey(String key)
        {
            this.key = key;
            return this;
        }

        public MetadataObjectBuilder setStringValue(String value)
        {
            Preconditions.checkArgument(MetadataDataType.StringType == type, "Data type mismatch");
            StringMapper mapper = new StringMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setIntegerValue(int value)
        {
            Preconditions.checkArgument(MetadataDataType.IntegerType == type, "Data type mismatch");
            IntegerMapper mapper = new IntegerMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setLongValue(long value)
        {
            Preconditions.checkArgument(MetadataDataType.LongType == type, "Data type mismatch");
            LongMapper mapper = new LongMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setBooleanValue(boolean value)
        {
            Preconditions.checkArgument(MetadataDataType.BooleanType == type, "Data type mismatch");
            BooleanMapper mapper = new BooleanMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setFloatValue(float value)
        {
            Preconditions.checkArgument(MetadataDataType.FloatType == type, "Data type mismatch");
            FloatMapper mapper = new FloatMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setDoubleValue(double value)
        {
            Preconditions.checkArgument(MetadataDataType.DoubleType == type, "Data type mismatch");
            DoubleMapper mapper = new DoubleMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setXmlValue(String value)
        {
            Preconditions.checkArgument(MetadataDataType.XMLType == type, "Data type mismatch");
            StringMapper mapper = new StringMapper();
            rawValue = mapper.toString(value);
            return this;
        }

        public MetadataObjectBuilder setJsonValue(JSONObject value)
        {
            Preconditions.checkArgument(MetadataDataType.JSONType == type, "Data type mismatch");
            StringMapper mapper = new StringMapper();

            try
            {
                rawValue = mapper.toString(value.toString(3));
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            return this;
        }

        public MetadataObjectBuilder setDateValue(Date value)
        {
            Preconditions.checkArgument(MetadataDataType.DateType == type, "Data type mismatch");
            return this;
        }

        public Metadata build()
        {
            Metadata object = new Metadata();
            object.dataType = this.type;
            object.account = this.account;
            object.referenceUrn = this.referenceUrn;
            object.entityReferenceType = this.entityReferenceType;
            object.key = this.key;
            object.rawValue = this.rawValue;

            return object;
        }
    }

    @JsonView(JsonGenerationView.Minimum.class)
    protected MetadataDataType dataType;

    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    @Size(max = KEY_MAX_LENGTH)
    protected String key;

    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    @Size(max = RAW_VALUE_MAX_LENGTH)
    protected String rawValue;

    @JsonView(JsonGenerationView.Full.class)
    protected String decodedValue = null;

    @Override
    public MetadataDataType getDataType()
    {
        return dataType;
    }

    @Override
    public void setDataType(MetadataDataType dataType)
    {
        this.dataType = dataType;
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public void setKey(String key)
    {
        this.key = key;
    }

    @Override
    public String getRawValue()
    {
        return this.rawValue;
    }

    @Override
    public void setRawValue(String value)
    {
        this.rawValue = value;
    }

    @Override
    public String getDecodedValue()
    {
        return decodedValue;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Metadata metadata = (Metadata) o;

        if (dataType != metadata.dataType) return false;
        if (decodedValue != null ? !decodedValue.equals(metadata.decodedValue) : metadata.decodedValue != null)
            return false;
        if (!key.equals(metadata.key)) return false;
        if (!rawValue.equals(metadata.rawValue)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + dataType.hashCode();
        result = 31 * result + key.hashCode();
        result = 31 * result + (rawValue != null ? rawValue.hashCode() : 0);
        result = 31 * result + (decodedValue != null ? decodedValue.hashCode() : 0);
        return result;
    }
}
