package net.smartcosmos.platform.jpa.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import net.smartcosmos.util.UuidUtil;
import net.smartcosmos.util.json.JsonGenerationView;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class DomainResourceEntity<T extends IDomainResource<T>>
        implements IDomainResource<T>, IPrePersistHandler, IPreUpdateHandler, IPostLoadHandler, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(length = 16, nullable = false, updatable = false, unique = true)
    @Type(type = "uuid-binary")
    @Id
    @JsonIgnore
    private UUID systemUuid;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    private long lastModifiedTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    @Column(length = 2048, nullable = true, updatable = true)
    private String moniker;

    @Override
    public void copy(final T target)
    {
        this.setSystemUuid(target.getSystemUuid());
        this.lastModifiedTimestamp = target.getLastModifiedTimestamp();
        this.moniker = target.getMoniker();
    }

    @Override
    public boolean equals(final Object obj)
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
        if (systemUuid == null)
        {
            if (other.systemUuid != null)
                return false;
        } else if (!systemUuid.equals(other.systemUuid))
            return false;
        return true;
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
    public UUID getSystemUuid()
    {
        return systemUuid;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @Override
    public String getUrn()
    {
        if (systemUuid == null)
        {
            return null;
        }
        return "urn:uuid:" + systemUuid.toString();
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
    @PostLoad
    public void onPostLoad()
    {

    }

    @Override
    @PrePersist
    public void onPrePersist()
    {
        lastModifiedTimestamp = System.currentTimeMillis();
        systemUuid = UuidUtil.getUuid();

        if (null != moniker && moniker.equals(Field.NULL_MONIKER))
        {
            moniker = null;
        }
    }

    @Override
    @PreUpdate
    public void onPreUpdate()
    {
        lastModifiedTimestamp = System.currentTimeMillis();

        if (null != moniker && moniker.equals(Field.NULL_MONIKER))
        {
            moniker = null;
        }
    }

    @Override
    public void setMoniker(final String moniker)
    {
        this.moniker = moniker;
    }

    /**
     * This setter is here for Hibernate to override so that it can set the database ID.
     * 
     * @param systemUuid
     *            the database id to set
     */
    protected void setSystemUuid(final UUID systemUuid)
    {
        this.systemUuid = systemUuid;
    }

    @Override
    public void setUrn(final String urn)
    {
        throw new UnsupportedOperationException("System UUID should only be set by the Database!");
    }
}
