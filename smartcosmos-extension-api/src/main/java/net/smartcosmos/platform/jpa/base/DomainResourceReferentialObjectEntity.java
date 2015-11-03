package net.smartcosmos.platform.jpa.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.util.UuidUtil;
import net.smartcosmos.util.json.JsonGenerationView;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedSuperclass
public abstract class DomainResourceReferentialObjectEntity<T extends IAccountDomainResource<T>>
        extends DomainResourceAccountEntity<T>implements IReferentialObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    @Index(name = "entity_reference_type_idx")
    @NotNull
    private EntityReferenceType entityReferenceType;

    @Column(length = 16, nullable = false, updatable = false)
    @Index(name = "entity_reference_urn_idx")
    @Type(type = "uuid-binary")
    @JsonIgnore
    private UUID referenceUuid;

    protected UUID getReferenceUuid()
    {
        return referenceUuid;
    }

    protected void setReferenceUuid(final UUID referenceUuid)
    {
        this.referenceUuid = referenceUuid;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }

    @Override
    public void copy(final T target)
    {
        super.copy(target);
    }

    /**
     * Serves as a reminder that the copy method does not cover this.
     */
    protected abstract void copyDomainResourceReferentialObjectEntity(final EntityReferenceType entityReferenceType,
            final String referenceUrn);

    @Override
    public void setEntityReferenceType(final EntityReferenceType entityReferenceType)
    {
        this.entityReferenceType = entityReferenceType;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @Override
    public String getReferenceUrn()
    {
        if (referenceUuid == null)
            return null;
        return UuidUtil.getUrnFromUuid(referenceUuid);
    }

    @Override
    public void setReferenceUrn(final String urn)
    {
        if (urn == null || urn.isEmpty())
        {
            this.referenceUuid = null;
        } else
        {
            this.referenceUuid = UuidUtil.getUuidFromUrn(urn);
        }
    }
}
