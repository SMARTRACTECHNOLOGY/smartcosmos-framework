package net.smartcosmos.client.impl;

import net.smartcosmos.client.connectivity.ServiceException;
import org.json.JSONObject;

/**
 * Type-safe update routines for SMART COSMOS entities either by providing an explicit strongly typed instance or
 * through a more fluid JSON definition. An update operation is predicated strictly upon providing a valid
 * system-assigned URN; if either a type-safe instance or a JSONObject is provided, only the
 * {@link net.smartcosmos.model.base.IUrnNamespace#getUrn()} method or {@link net.smartcosmos.Field#URN_FIELD} field is
 * utilized by the update routines to find the original persisted object. Once that object is found, the new values
 * are merged with the existing values.
 * <p/>
 * <b>NOTE:</b> For details on how to reset an entity's moniker field back to a <code>NULL</code> value after it has been assigned
 * a non-null value, please refer to the class {@link net.smartcosmos.model.base.IMoniker} JavaDocs.
 *
 * @param <T> One of the updateable SMART COSMOS contextual entities.
 * @see net.smartcosmos.platform.api.extension.IExtension
 * @see net.smartcosmos.objects.model.context.IDevice
 * @see net.smartcosmos.platform.api.geo.IGeospatialEntry
 * @see net.smartcosmos.objects.model.context.IObject
 * @see net.smartcosmos.objects.model.context.IObjectAddress
 * @see net.smartcosmos.objects.model.context.IObjectInteractionSession
 * @see net.smartcosmos.objects.model.context.ITimelineEntry
 * @see net.smartcosmos.model.context.IUser
 */
public interface IUpdateableBaseClient<T> extends ICreateableBaseClient<T>
{
    /**
     * Submits every field for update. Use this method if you aren't sure what fields changed or if
     * you aren't concerned about network utilization.
     *
     * @param instance
     */
    void update(T instance) throws ServiceException;

    void update(JSONObject instance) throws ServiceException;
}
