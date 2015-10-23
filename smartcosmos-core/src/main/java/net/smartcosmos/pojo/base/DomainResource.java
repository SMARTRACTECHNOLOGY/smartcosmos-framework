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
import net.smartcosmos.util.UuidUtil;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.validation.constraints.Size;
import java.util.UUID;

@JsonPropertyOrder(value = {"urn", "lastModifiedTimestamp" })
public abstract class DomainResource<T> implements IDomainResource<T>
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected UUID systemUuid;

    @JsonView(JsonGenerationView.Standard.class)
    protected long lastModifiedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    @Size(max = 1024)
    protected String moniker;

    @Override
    public void setUrn(String urn)

    {
        this.systemUuid = UuidUtil.getUuidFromUrn(urn);
    }

    @Override
    public String getUrn()
    {
        return UuidUtil.getUrnFromUuid(systemUuid);
    }

    @Override
    public UUID getSystemUuid()
    {
        return systemUuid;
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
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DomainResource other = (DomainResource) obj;
        if (lastModifiedTimestamp != other.lastModifiedTimestamp)
            return false;
        if (moniker == null)
        {
            if (other.moniker != null)
                return false;
        } else if (!moniker.equals(other.moniker))
            return false;
        if (systemUuid == null)
        {
            if (other.systemUuid != null)
                return false;
        } else if (!systemUuid.equals(other.systemUuid))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (lastModifiedTimestamp ^ (lastModifiedTimestamp >>> 32));
        result = prime * result + ((moniker == null) ? 0 : moniker.hashCode());
        result = prime * result + ((systemUuid == null) ? 0 : systemUuid.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "DomainResource [systemUuid=" + systemUuid + ", lastModifiedTimestamp=" + lastModifiedTimestamp +
                ", moniker=" + moniker + "]";
    }
}
