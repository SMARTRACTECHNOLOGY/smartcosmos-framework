package net.smartcosmos.model.base;

/**
 * Internal interface not suitable for public use. Captures the unique key assigned by the underlying persistence
 * engine, and a few helper functions.
 *
 * @param <T> Domain object type from the object model, e.g. <code>IObject</code>
 */
public interface IDomainResource<T> extends IUrnNamespace, IMoniker
{
    long getUniqueId();

    void setUniqueId(long uniqueId);

    void copy(T object);
}
