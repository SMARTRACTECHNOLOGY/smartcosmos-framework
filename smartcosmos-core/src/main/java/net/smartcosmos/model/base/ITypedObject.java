
package net.smartcosmos.model.base;

/**
 * Establishes an ontological set of categories within a collection of
 * peer domain resources. For example, a set of five different type values
 * may be used exclusively to categorize all of the account's
 * {@link net.smartcosmos.objects.model.context.IObject} instances.
 * <p/>
 * The ontological categories are account specific; each account context will
 * have its own unique ontology.
 */
public interface ITypedObject
{
    String getType();

    void setType(String type);
}
