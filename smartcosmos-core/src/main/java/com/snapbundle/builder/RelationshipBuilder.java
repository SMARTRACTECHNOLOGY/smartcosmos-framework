/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.builder;

import com.google.common.base.Preconditions;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.pojo.context.Relationship;

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
        Preconditions.checkNotNull(urn);
        instance.setRelatedReferenceUrn(urn);
        return this;
    }

    public RelationshipBuilder setRelatedEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        Preconditions.checkNotNull(entityReferenceType);
        instance.setRelatedEntityReferenceType(entityReferenceType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
        Preconditions.checkNotNull(instance.getReferenceUrn(), "reference urn must not be null");
        Preconditions.checkNotNull(instance.getRelatedReferenceUrn(), "related reference urn must not be null");
        Preconditions.checkNotNull(instance.getEntityReferenceType(), "entity reference type must not be null");
        Preconditions.checkNotNull(instance.getRelatedEntityReferenceType(), "related entity reference type must not be null");
    }
}
