

package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Captures binary or true-false conditions between objects. A relationship could capture
 * that Jason "owns" a bank account, whereas an {@link IObjectInteraction} would
 * be used to capture every deposit or withdraw from the account.
 * <p/>
 * If you are looking to capture a recurring pattern of interactivity, the recommended construct is
 * to use an {@link IObjectInteraction} instead of a relationship.
 */
public interface IRelationship extends IAccountDomainResource<IRelationship>, IReferentialObject, ITypedObject
{
    String getRelatedReferenceUrn();

    void setRelatedReferenceUrn(String urn);

    EntityReferenceType getRelatedEntityReferenceType();

    void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType);
}
