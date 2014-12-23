package net.smartcosmos.model.base;

/**
 * Provides a human readable name and optional descriptive narrative. Named objects typically are marked <i>inactive</i>
 * instead of being physically deleted to preserve historical reporting capabilities.
 *
 * @param <T> Concrete domain object type from the object model.
 */
public interface INamedObject<T> extends IDomainResource<T>
{
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    boolean isActive();

    void setActive(boolean flag);
}
