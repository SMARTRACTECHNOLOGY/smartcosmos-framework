package com.snapbundle.model.context;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.IReferentialObject;

public interface IRelationship extends IAccountDomainResource<IRelationship>, IReferentialObject
{
    IRelationshipType getRelationshipType();

    void setRelationshipType(IRelationshipType relationshipType);

    String getRelatedReferenceUrn();

    void setRelatedReferenceUrn(String urn);

    EntityReferenceType getRelatedEntityReferenceType();

    void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType);
}
