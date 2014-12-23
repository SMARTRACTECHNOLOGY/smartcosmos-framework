package net.smartcosmos.client.objects.extension;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

public interface IExtensionClient extends IUpdateableBaseClient<IExtension>, IDeleteableBaseClient<IExtension>
{
    Collection<IExtension> findByNameLike(String nameLike) throws ServiceException;

    Collection<IExtension> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;

    Collection<IExtension> catalog() throws ServiceException;

    IExtension getPublishedExtension(String urn) throws ServiceException;
}
