package com.snapbundle.model.context;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.base.IDomainResource;
import com.snapbundle.model.base.IReferentialObject;

public interface IRelationship extends IDomainResource<IRelationship>, IReferentialObject
{
    RelationshipType getRelationshipType();

    void setRelationshipType(RelationshipType relationshipType);

    String getRelatedReferenceUrn();

    void setRelatedReferenceUrn(String urn);

    EntityReferenceType getRelatedEntityReferenceType();

    void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType);
}
