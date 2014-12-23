package net.smartcosmos.pojo.context;

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

import java.util.Arrays;
import java.util.Date;

public class Metadata extends ReferentialObject<IMetadata> implements IMetadata
{
    public static class MetadataObjectBuilder
    {
        private MetadataDataType type;

        private String key;

        private byte[] rawValue;

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
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setIntegerValue(int value)
        {
            Preconditions.checkArgument(MetadataDataType.IntegerType == type, "Data type mismatch");
            IntegerMapper mapper = new IntegerMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setLongValue(long value)
        {
            Preconditions.checkArgument(MetadataDataType.LongType == type, "Data type mismatch");
            LongMapper mapper = new LongMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setBooleanValue(boolean value)
        {
            Preconditions.checkArgument(MetadataDataType.BooleanType == type, "Data type mismatch");
            BooleanMapper mapper = new BooleanMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setFloatValue(float value)
        {
            Preconditions.checkArgument(MetadataDataType.FloatType == type, "Data type mismatch");
            FloatMapper mapper = new FloatMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setDoubleValue(double value)
        {
            Preconditions.checkArgument(MetadataDataType.DoubleType == type, "Data type mismatch");
            DoubleMapper mapper = new DoubleMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setXmlValue(String value)
        {
            Preconditions.checkArgument(MetadataDataType.XMLType == type, "Data type mismatch");
            StringMapper mapper = new StringMapper();
            rawValue = mapper.toBytes(value);
            return this;
        }

        public MetadataObjectBuilder setJsonValue(JSONObject value)
        {
            Preconditions.checkArgument(MetadataDataType.JSONType == type, "Data type mismatch");
            StringMapper mapper = new StringMapper();

            try
            {
                rawValue = mapper.toBytes(value.toString(3));
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
    protected String key;

    @JsonView(JsonGenerationView.Minimum.class)
    protected byte[] rawValue;

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
    public byte[] getRawValue()
    {
        return Arrays.copyOf(rawValue, rawValue.length);
    }

    @Override
    public void setRawValue(byte[] value)
    {
        rawValue = Arrays.copyOf(value, value.length);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Metadata metadata = (Metadata) o;

        if (dataType != metadata.dataType) return false;
        if (!key.equals(metadata.key)) return false;
        if (!Arrays.equals(rawValue, metadata.rawValue)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + dataType.hashCode();
        result = 31 * result + key.hashCode();
        result = 31 * result + Arrays.hashCode(rawValue);
        return result;
    }
}
