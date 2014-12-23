package net.smartcosmos.client.objects.interaction.session;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link net.smartcosmos.objects.model.context.IObjectInteractionSession} instances.
 */
public interface IInteractionSessionClient extends IUpdateableBaseClient<IObjectInteractionSession>
{
    /**
     * Queries for sessions with names that start with the case-sensitive pattern, returning all matches using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of sessions that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IObjectInteractionSession> findByNameLike(String nameLike) throws ServiceException;

    /**
     * Queries for sessions with names that start with the case-sensitive pattern, returning all matches using the
     * specified field verbosity.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of sessions that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IObjectInteractionSession> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;
}
