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
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Metadata extends ReferentialObject<IMetadata> implements IMetadata
{
    public static class MetadataObjectBuilder
    {

        public static final String RFC_3339_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

        SimpleDateFormat dateFormat = new SimpleDateFormat(RFC_3339_FORMAT);

        private MetadataDataType type;

        private String key;

        private String value;

        private IAccount account;

        private EntityReferenceType entityReferenceType;

        private UUID referenceUrn;

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
            if (urn == null || urn.isEmpty())
            {
                this.referenceUrn = null;
            } else
            {
                this.referenceUrn = UUID.fromString(urn);
            }
            return this;
        }

        public MetadataObjectBuilder setKey(String key)
        {
            this.key = key;
            return this;
        }

        public MetadataObjectBuilder setStringValue(String inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.StringType == type, "Data type mismatch");
            value = inputValue;
            return this;
        }

        public MetadataObjectBuilder setIntegerValue(int inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.IntegerType == type, "Data type mismatch");
            value = Integer.toString(inputValue);
            return this;
        }

        public MetadataObjectBuilder setLongValue(long inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.LongType == type, "Data type mismatch");
            value = Long.toString(inputValue);
            return this;
        }

        public MetadataObjectBuilder setBooleanValue(boolean inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.BooleanType == type, "Data type mismatch");
            if (inputValue)
            {
                value = "true";
            }
            value = "false";

            return this;
        }

        public MetadataObjectBuilder setFloatValue(float inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.FloatType == type, "Data type mismatch");
            value = Float.toString(inputValue);
            return this;
        }

        public MetadataObjectBuilder setDoubleValue(double inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.DoubleType == type, "Data type mismatch");
            value = Double.toString(inputValue);
            return this;
        }

        public MetadataObjectBuilder setXmlValue(String inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.XMLType == type, "Data type mismatch");
            value = inputValue;
            return this;
        }

        public MetadataObjectBuilder setJsonValue(JSONObject inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.JSONType == type, "Data type mismatch");
            value = inputValue.toString();
            return this;
        }

        public MetadataObjectBuilder setDateValue(Date inputValue)
        {
            Preconditions.checkArgument(MetadataDataType.DateType == type, "Data type mismatch");
            value = dateFormat.format(inputValue);
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
            object.value = this.value;

            return object;
        }
    }

    @JsonView(JsonGenerationView.Minimum.class)
    protected MetadataDataType dataType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String key;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String value;

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
    public String getValue()
    {
        return value;
    }

    @Override
    public void setValue(String value)
    {
        this.value = value;
    }

//    @Override
//    public String getDecodedValue()
//    {
//        return decodedValue;
//    }

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
        if (!value.equals(metadata.value)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + dataType.hashCode();
        result = 31 * result + key.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (decodedValue != null ? decodedValue.hashCode() : 0);
        return result;
    }
}
