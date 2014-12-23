package net.smartcosmos.client.objects.timeline;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.objects.model.context.ITimelineEntry;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, updates, or queries for {@link net.smartcosmos.objects.model.context.ITimelineEntry} instances.
 */
public interface ITimelineClient extends IUpdateableBaseClient<ITimelineEntry>
{
    /**
     * Queries for timeline entries with names that start with the case-sensitive pattern, returning all matches using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of timeline entries that have a name that matches the specified
     * pattern
     * @throws ServiceException
     */
    Collection<ITimelineEntry> findByNameLike(String nameLike) throws ServiceException;

    /**
     * Queries for timeline entries with names that start with the case-sensitive pattern, returning all matches using
     * the specified field verbosity.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of timeline entries that have a name that matches the specified
     * pattern
     * @throws ServiceException
     */
    Collection<ITimelineEntry> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;
}
