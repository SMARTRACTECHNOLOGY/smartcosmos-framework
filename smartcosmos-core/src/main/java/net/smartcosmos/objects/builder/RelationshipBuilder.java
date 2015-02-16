package net.smartcosmos.objects.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.builder.AbstractMonikerBuilder;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.objects.pojo.context.Relationship;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IRelationship}
 * instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link net.smartcosmos.Field#REFERENCE_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#RELATED_ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link net.smartcosmos.Field#RELATED_REFERENCE_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class RelationshipBuilder extends AbstractMonikerBuilder<IRelationship, RelationshipBuilder>
{
    public RelationshipBuilder(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        super(new Relationship());
        instance.setEntityReferenceType(entityReferenceType);
        instance.setReferenceUrn(referenceUrn);
    }

    public RelationshipBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public RelationshipBuilder setRelatedReferenceUrn(String urn)
    {
        checkNotNull(urn);
        instance.setRelatedReferenceUrn(urn);
        return this;
    }

    public RelationshipBuilder setRelatedEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        checkNotNull(entityReferenceType);
        instance.setRelatedEntityReferenceType(entityReferenceType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        checkNotNull(instance.getType(), "type must not be null");
        checkNotNull(instance.getReferenceUrn(), "reference urn must not be null");
        checkNotNull(instance.getRelatedReferenceUrn(), "related reference urn must not be null");
        checkNotNull(instance.getEntityReferenceType(), "entity reference type must not be null");
        checkNotNull(instance.getRelatedEntityReferenceType(), "related entity reference type must not be null");
    }
}
