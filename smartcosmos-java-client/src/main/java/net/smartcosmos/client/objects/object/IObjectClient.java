package net.smartcosmos.client.objects.object;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.client.impl.endpoint.ObjectEndpoints;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link net.smartcosmos.objects.model.context.IObject} instances.
 */
public interface IObjectClient extends IUpdateableBaseClient<IObject>
{
    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} using a case-sensitive
     * exact match.
     * <p/>
     * Returns a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @return Matching object
     */
    IObject findByExactObjectUrn(String objectUrn) throws ServiceException;

    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} using a case-sensitive
     * exact match.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param viewType  Field verbosity
     * @return Matching object
     */
    IObject findByExactObjectUrn(String objectUrn, ViewType viewType) throws ServiceException;

    /**
     * Complex query for a collection of matching objects.
     *
     * @param builder Builder that defines the query to perform
     * @return Non-null collection of matching entities; collection may have a size of 0 to indicate no matches found
     */
    Collection<IObject> query(ObjectEndpoints.Builder builder) throws ServiceException;
}
