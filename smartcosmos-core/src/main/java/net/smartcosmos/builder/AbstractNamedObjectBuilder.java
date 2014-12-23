

package net.smartcosmos.builder;

import net.smartcosmos.model.base.INamedObject;

public abstract class AbstractNamedObjectBuilder<T extends INamedObject, E> extends AbstractMonikerBuilder<T, E>
{
    protected AbstractNamedObjectBuilder(T instance)
    {
        super(instance);
        instance.setActive(true);
    }

    @SuppressWarnings("unchecked")
    public E setName(String name)
    {
        instance.setName(name);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E setDescription(String description)
    {
        instance.setDescription(description);
        return (E) this;
    }

    @SuppressWarnings("unchecked")
    public E setActive(boolean flag)
    {
        instance.setActive(flag);
        return (E) this;
    }
}
