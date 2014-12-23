package net.smartcosmos.client.objects.device;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.objects.model.context.IDevice;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, updates, or queries for {@link net.smartcosmos.objects.model.context.IDevice} instances.
 */
public interface IDeviceClient extends IUpdateableBaseClient<IDevice>
{
    /**
     * Queries for devices with names that start with the case-sensitive pattern, returning all matches using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of devices that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IDevice> findByNameLike(String nameLike) throws ServiceException;

    /**
     * Queries for devices with names that start with the case-sensitive pattern, returning all matches using the
     * specified field verbosity.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of devices that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IDevice> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;

    /**
     * Locates a specific {@link net.smartcosmos.objects.model.context.IDevice} that has an exact case-sensitive match to the
     * specified natural key using a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param identification Case-sensitive device identification
     * @return Matching device
     * @throws ServiceException thrown if no device with the specified case-sensitive identification exists
     */
    IDevice findByDeviceIdentification(String identification) throws ServiceException;

    /**
     * Locates a specific {@link net.smartcosmos.objects.model.context.IDevice} that has an exact case-sensitive match to the
     * specified natural key using a the specified field verbosity.
     *
     * @param identification Case-sensitive device identification
     * @param viewType       Field verbosity
     * @return Matching device
     * @throws ServiceException thrown if no device with the specified case-sensitive identification exists
     */
    IDevice findByDeviceIdentification(String identification, ViewType viewType) throws ServiceException;
}
