package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Foundational artifact within the SMART COSMOS Objects platform used to represent
 * nouns, e.g. people, bank accounts, vehicles, parks, buildings, etc.
 * <p/>
 * All objects are {@link net.smartcosmos.model.base.INamedObject} instances so
 * that a human-readable {@link net.smartcosmos.model.base.INamedObject#getName()}
 * can be assigned.
 * <p/>
 * This is the only class within the SMART COSMOS Objects platform that replaces the
 * system-assigned URN with an arbitrary developer-defined
 * {@link #getObjectUrn()}. This <code>objectUrn</code> must be unique within
 * the account context. It is up to the developer to devise a URN strategy that
 * meets this requirement.
 */
public interface IObject extends IAccountDomainResource<IObject>, INamedObject<IObject>, ITypedObject
{
    String getObjectUrn();

    void setObjectUrn(String urn);
}
