package net.smartcosmos.client.impl;

import net.smartcosmos.client.connectivity.ServiceException;
import org.json.JSONObject;

/**
 * Type-safe deletion routines for SMART COSMOS entities that support permanent removal. Deletion is predicated strictly
 * upon providing a valid system-assigned URN; if either a type-safe instance or a JSONObject is provided, only the
 * {@link net.smartcosmos.model.base.IUrnNamespace#getUrn()} method or {@link net.smartcosmos.Field#URN_FIELD} field is
 * utilized by the deletion routines.
 * <p/>
 * Not every creatable entity supports deletion by design. Some entities, like
 * {@link net.smartcosmos.objects.model.context.IObject} are explicitly preserved for historical reporting purposes and should be
 * set inactive with the {@link net.smartcosmos.model.base.INamedObject#setActive(boolean)} method.
 *
 * @param <T> One of the deletable SMART COSMOS contextual entities.
 * @see net.smartcosmos.objects.model.context.IFile
 * @see net.smartcosmos.platform.api.extension.IExtension
 * @see net.smartcosmos.model.context.IMetadata
 * @see net.smartcosmos.objects.model.context.IRelationship
 * @see net.smartcosmos.objects.model.context.ITag
 * @see net.smartcosmos.objects.model.context.IObjectAddress
 */
public interface IDeleteableBaseClient<T>
{
    /**
     * Removes an existing instance, if it exists.
     *
     * @param instance
     * @throws net.smartcosmos.client.connectivity.ServiceException
     */
    void delete(T instance) throws ServiceException;

    /**
     * Removes an existing instance based on the {@link net.smartcosmos.Field#URN_FIELD} value, if it exists.
     *
     * @param instance
     * @throws ServiceException
     */
    void delete(JSONObject instance) throws ServiceException;
}
