

package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IObject} instances.
 * <p/>
 * The minimum fields required to define a new Object are
 * <ul>
 * <li>{@link net.smartcosmos.Field#OBJECT_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class ObjectBuilder extends AbstractNamedObjectBuilder<IObject, ObjectBuilder>
{
    public ObjectBuilder(String objectUrn)
    {
        super(new ObjectImpl());

        Preconditions.checkNotNull(objectUrn);
        instance.setObjectUrn(objectUrn);
    }

    public ObjectBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public ObjectBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }
}
