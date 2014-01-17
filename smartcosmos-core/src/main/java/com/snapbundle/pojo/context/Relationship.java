package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.json.JsonGenerationView;

public class Relationship extends ReferentialObject<IRelationship> implements IRelationship
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected EntityReferenceType relatedEntityReferenceType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String relatedReferenceURN;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String type;

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
        return relatedReferenceURN;
    }

    @Override
    public void setRelatedReferenceUrn(String urn)
    {
        this.relatedReferenceURN = urn;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Relationship that = (Relationship) o;

        if (relatedEntityReferenceType != that.relatedEntityReferenceType) return false;
        if (!relatedReferenceURN.equals(that.relatedReferenceURN)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + relatedEntityReferenceType.hashCode();
        result = 31 * result + relatedReferenceURN.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
