package com.snapbundle.model.context;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.IReferentialObject;
import com.snapbundle.model.base.ITypedObject;

public interface IRelationship extends IAccountDomainResource<IRelationship>, IReferentialObject, ITypedObject
{
    String getRelatedReferenceUrn();

    void setRelatedReferenceUrn(String urn);

    EntityReferenceType getRelatedEntityReferenceType();

    void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType);
}
