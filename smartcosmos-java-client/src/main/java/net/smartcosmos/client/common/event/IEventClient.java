package net.smartcosmos.client.common.event;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IFindableBaseClient;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

public interface IEventClient extends IFindableBaseClient<IEvent>
{
    Collection<IEvent> findByEventType(EventType eventType) throws ServiceException;

    Collection<IEvent> findByEventType(EventType eventType, ViewType viewType) throws ServiceException;

    Collection<IEvent> findSince(long timestamp) throws ServiceException;

    Collection<IEvent> findSince(long timestamp, ViewType viewType) throws ServiceException;
}
