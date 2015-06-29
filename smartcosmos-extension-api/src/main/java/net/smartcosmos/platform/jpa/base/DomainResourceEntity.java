package net.smartcosmos.platform.jpa.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.Field;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.platform.jpa.integrator.IPostLoadHandler;
import net.smartcosmos.platform.jpa.integrator.IPrePersistHandler;
import net.smartcosmos.platform.jpa.integrator.IPreUpdateHandler;
import net.smartcosmos.platform.util.UuidUtil;
import net.smartcosmos.util.json.JsonGenerationView;

import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class DomainResourceEntity<T extends IDomainResource> implements IDomainResource<T>,
        IPrePersistHandler, IPreUpdateHandler, IPostLoadHandler
{
    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 16, nullable = false, updatable = false, unique = true)
    @Type(type = "uuid-binary")
    @Id
    UUID urn;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    protected long lastModifiedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    @Column(length = 2048, nullable = true, updatable = true)
    protected String moniker;

    @Override
    public String getUrn()
    {
        if (urn == null)
        {
            return null;
        }
        return urn.toString();
    }

    @Override
    public void setUrn(String urn)
    {
        if (urn == null)
        {
            this.urn = null;
        } else
        {
            this.urn = UUID.fromString(urn);
        }
    }

    @Override
    public void copy(T target)
    {
        if (target.getUrn() == null)
        {
            this.urn = null;
        } else
        {
            this.urn = UUID.fromString(target.getUrn());
        }
        this.lastModifiedTimestamp = target.getLastModifiedTimestamp();
        this.moniker = target.getMoniker();
    }

    @Override
    public void onPostLoad()
    {

    }

    @Override
    public void onPrePersist()
    {
        lastModifiedTimestamp = System.currentTimeMillis();
        setUrn(UuidUtil.getUuidAsString());

        if (null != moniker && moniker.equals(Field.NULL_MONIKER))
        {
            moniker = null;
        }
    }

    @Override
    public void onPreUpdate()
    {
        lastModifiedTimestamp = System.currentTimeMillis();

        if (null != moniker && moniker.equals(Field.NULL_MONIKER))
        {
            moniker = null;
        }
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DomainResourceEntity other = (DomainResourceEntity) obj;
        if (lastModifiedTimestamp != other.lastModifiedTimestamp)
            return false;
        if (moniker == null)
        {
            if (other.moniker != null)
                return false;
        } else if (!moniker.equals(other.moniker))
            return false;
        if (urn == null)
        {
            if (other.urn != null)
                return false;
        } else if (!urn.equals(other.urn))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (lastModifiedTimestamp ^ (lastModifiedTimestamp >>> 32));
        result = prime * result + ((moniker == null) ? 0 : moniker.hashCode());
        result = prime * result + ((urn == null) ? 0 : urn.hashCode());
        return result;
    }
}
