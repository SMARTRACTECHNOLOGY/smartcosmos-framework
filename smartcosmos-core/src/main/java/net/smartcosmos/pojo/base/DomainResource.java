package net.smartcosmos.pojo.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

@JsonPropertyOrder(value = {"uniqueId", "urn", "lastModifiedTimestamp"})
public abstract class DomainResource<T> implements IDomainResource<T>
{
    @JsonView(JsonGenerationView.Restricted.class)
    protected long uniqueId;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String urn;

    @JsonView(JsonGenerationView.Standard.class)
    protected long lastModifiedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    protected String moniker;

    @Override
    public long getUniqueId()
    {
        return uniqueId;
    }

    @Override
    public void setUniqueId(long uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    @Override
    public void setUrn(String urn)
    {
        this.urn = urn;
    }

    @Override
    public String getUrn()
    {
        return urn;
    }

    @Override
    public long getLastModifiedTimestamp()
    {
        return lastModifiedTimestamp;
    }

    @Override
    public String getMoniker()
    {
        return moniker;
    }

    @Override
    public void setMoniker(String moniker)
    {
        this.moniker = moniker;
    }

    @Override
    public void copy(T target)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainResource that = (DomainResource) o;

        if (lastModifiedTimestamp != that.lastModifiedTimestamp) return false;
        if (moniker != null ? !moniker.equals(that.moniker) : that.moniker != null) return false;
        if (!urn.equals(that.urn)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = urn.hashCode();
        result = 31 * result + (int) (lastModifiedTimestamp ^ (lastModifiedTimestamp >>> 32));
        result = 31 * result + (moniker != null ? moniker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "DomainResource{" +
                "uniqueId=" + uniqueId +
                ", urn='" + urn + '\'' +
                ", lastModifiedTimestamp=" + lastModifiedTimestamp +
                ", moniker='" + moniker + '\'' +
                '}';
    }
}
