package net.smartcosmos.pojo.base;

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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

@JsonPropertyOrder(value = {"uniqueId", "urn", "lastModifiedTimestamp" })
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
