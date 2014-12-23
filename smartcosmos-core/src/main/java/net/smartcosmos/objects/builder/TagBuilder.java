package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.objects.model.context.ITag;
import net.smartcosmos.objects.pojo.context.Tag;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.ITag} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * </ul>
 */
public final class TagBuilder extends AbstractNamedObjectBuilder<ITag, TagBuilder>
{
    public TagBuilder()
    {
        super(new Tag());
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
    }
}
