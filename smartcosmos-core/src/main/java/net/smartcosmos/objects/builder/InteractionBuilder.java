package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractReferentialBuilder;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectInteraction;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.objects.pojo.context.ObjectInteraction;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IObjectInteraction} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class InteractionBuilder extends AbstractReferentialBuilder<IObjectInteraction, InteractionBuilder>
{
    public InteractionBuilder(long recordedTimestamp)
    {
        super(new ObjectInteraction());
        instance.setRecordedTimestamp(recordedTimestamp);
    }

    public InteractionBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public InteractionBuilder setObjectInteractionSession(IObjectInteractionSession session)
    {
        instance.setObjectInteractionSession(session);
        return this;
    }

    public InteractionBuilder setObject(IObject interactedObject)
    {
        instance.setObject(interactedObject);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }
}
