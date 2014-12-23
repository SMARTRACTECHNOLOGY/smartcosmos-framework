package net.smartcosmos.client.objects.geospatial;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.model.geo.IGeospatialEntry;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, updates, or queries for {@link net.smartcosmos.model.geo.IGeospatialEntry} instances.
 */
public interface IGeospatialClient extends IUpdateableBaseClient<IGeospatialEntry>
{
    /**
     * Queries for geospatial definitions with names that start with the case-sensitive pattern, returning all matches
     * using a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of geospatial definitions that have a name that matches the
     * specified pattern
     * @throws ServiceException
     */
    Collection<IGeospatialEntry> findByNameLike(String nameLike) throws ServiceException;

    /**
     * Queries for geospatial definitions with names that start with the case-sensitive pattern, returning all matches
     * using the specified field verbosity.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of geospatial definitions that have a name that matches the
     * specified pattern
     * @throws ServiceException
     */
    Collection<IGeospatialEntry> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;
}
