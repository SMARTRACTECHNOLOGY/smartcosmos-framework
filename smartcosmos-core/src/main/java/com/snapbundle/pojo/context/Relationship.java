package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.model.context.IRelationshipType;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.json.JsonGenerationView;

public class Relationship extends ReferentialObject<IRelationship> implements IRelationship
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected EntityReferenceType relatedEntityReferenceType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String relatedReferenceURN;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = RelationshipType.class)
    protected IRelationshipType relationshipType;

    @Override
    public IRelationshipType getRelationshipType()
    {
        return relationshipType;
    }

    @Override
    public void setRelationshipType(IRelationshipType relationshipType)
    {
        this.relationshipType = relationshipType;
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
    public void copy(IRelationship object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
