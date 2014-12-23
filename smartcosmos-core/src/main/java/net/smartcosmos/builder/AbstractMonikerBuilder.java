package net.smartcosmos.builder;

import net.smartcosmos.model.base.IMoniker;

public abstract class AbstractMonikerBuilder<T extends IMoniker, E> extends AbstractBuilder<T>
{
    protected AbstractMonikerBuilder(T instance)
    {
        super(instance);
    }

    @SuppressWarnings("unchecked")
    public E setMoniker(String moniker)
    {
        instance.setMoniker(moniker);
        return (E) this;
    }
}
