

package net.smartcosmos.builder;

/**
 * Generic Builder pattern base class that includes a path for validation and minimum creation definition.
 *
 * @param <T>
 */
public class AbstractBuilder<T>
{
    protected final T instance;

    protected AbstractBuilder(T instance)
    {
        this.instance = instance;
    }

    public T build()
    {
        onInject();
        onValidate();
        return instance;
    }

    protected void onValidate()
    {

    }

    protected void onInject()
    {

    }

    public boolean meetsCreationMinimum()
    {
        return true;
    }
}
