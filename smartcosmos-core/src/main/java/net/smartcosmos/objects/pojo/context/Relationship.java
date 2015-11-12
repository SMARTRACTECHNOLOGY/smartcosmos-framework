package net.smartcosmos.objects.pojo.context;

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
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.pojo.base.ReferentialObject;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Relationship extends ReferentialObject<IRelationship> implements IRelationship
{
    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    protected EntityReferenceType relatedEntityReferenceType;

    @JsonView(JsonGenerationView.Minimum.class)
    @NotNull
    protected String relatedReferenceUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    @Size(max = TYPE_MAX_LENGTH)
    @NotNull
    protected String type;

    @JsonView(JsonGenerationView.Full.class)
    protected boolean reciprocal = false;

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        Preconditions.checkNotNull(type, "type must not be null");
        this.type = type;
    }

    @Override
    public String getRelatedReferenceUrn()
    {
        return relatedReferenceUrn;
    }

    @Override
    public void setRelatedReferenceUrn(String urn)
    {
        this.relatedReferenceUrn = urn;
    }

    @Override
    public EntityReferenceType getRelatedEntityReferenceType()
    {
        return relatedEntityReferenceType;
    }

    @Override
    public void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        this.relatedEntityReferenceType = entityReferenceType;
    }

    public boolean isReciprocal()
    {
        return reciprocal;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Relationship that = (Relationship) o;

        if (relatedEntityReferenceType != that.relatedEntityReferenceType) return false;
        if (!relatedReferenceUrn.equals(that.relatedReferenceUrn)) return false;
        if (reciprocal != that.reciprocal) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();

        result = 31 * result + ((relatedEntityReferenceType == null) ? 0 : relatedEntityReferenceType.hashCode());
        result = 31 * result + ((relatedReferenceUrn == null) ? 0 : relatedReferenceUrn.hashCode());
        result = 31 * result + ((type == null) ? 0 : type.hashCode());
        result = 31 * result + Boolean.valueOf(reciprocal).hashCode();
        return result;
    }
}
