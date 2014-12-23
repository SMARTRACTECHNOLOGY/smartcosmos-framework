package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IAccountContext;
import net.smartcosmos.model.base.IMoniker;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.context.IAccount;

public abstract class AbstractReferentialBuilder<T extends IMoniker, E> extends AbstractMonikerBuilder<T, E>
{
    protected AbstractReferentialBuilder(T instance)
    {
        super(instance);
    }

    public E setAccount(IAccount account)
    {
        ((IAccountContext) instance).setAccount(account);
        return (E) this;
    }

    public E setReferenceUrn(String urn)
    {
        Preconditions.checkNotNull(urn);
        ((IReferentialObject) instance).setReferenceUrn(urn);
        return (E) this;
    }

    public E setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        Preconditions.checkNotNull(entityReferenceType);
        ((IReferentialObject) instance).setEntityReferenceType(entityReferenceType);
        return (E) this;
    }
}
