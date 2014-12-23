

package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.objects.pojo.context.ObjectInteractionSession;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IObjectInteractionSession}
 * instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#START_TIMESTAMP_FIELD}</li>
 * </ul>
 */
public class InteractionSessionBuilder extends AbstractNamedObjectBuilder<IObjectInteractionSession, InteractionSessionBuilder>
{
    public InteractionSessionBuilder(long startTimestamp)
    {
        super(new ObjectInteractionSession());
        instance.setStartTimestamp(startTimestamp);
    }

    public InteractionSessionBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkState(instance.getStartTimestamp() > 0, "start timestamp must be a positive value");
    }
}

