package net.smartcosmos.objects.builder;

import net.smartcosmos.objects.model.context.ITag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Convenience builder for assembling a collection of {@link net.smartcosmos.objects.model.context.ITag} instances
 * to define via a singular call.
 */
public final class TagCollectionBuilder
{
    Collection<ITag> tagCollection = new ArrayList<>();

    public TagCollectionBuilder addTag(String name)
    {
        tagCollection.add(new TagBuilder().setName(name).build());
        return this;
    }

    public Collection<ITag> build()
    {
        return Collections.unmodifiableCollection(tagCollection);
    }
}
