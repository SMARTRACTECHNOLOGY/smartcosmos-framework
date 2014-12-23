

package net.smartcosmos.model.base;

/**
 * Defines an explicit relationship between two otherwise disparate domain
 * resources <i>within the same account context</i>. The pairing is always
 * based on the combination of the {@link EntityReferenceType}
 * and the system-assigned URN, the "reference urn."
 */
public interface IReferentialObject extends IMinimalReferentialObject, IAccountContext
{
    void setReferenceUrn(String urn);

    void setEntityReferenceType(EntityReferenceType entityReferenceType);
}
