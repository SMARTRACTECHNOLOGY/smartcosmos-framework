package net.smartcosmos.client.common.event;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractFindableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.EventEndpoints;
import net.smartcosmos.pojo.event.Event;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.util.json.ViewType;

import java.util.ArrayList;
import java.util.Collection;

class EventClient extends AbstractFindableBaseClient<IEvent> implements IEventClient
{
    EventClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IEvent findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, EventEndpoints.findByUrn(urn, viewType), Event.class);
    }

    @Override
    public Collection<IEvent> findByEventType(EventType eventType) throws ServiceException
    {
        return findByEventType(eventType, ViewType.Standard);
    }

    @Override
    public Collection<IEvent> findByEventType(EventType eventType, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IEvent> command = new GetCollectionCommand<>(context);
        return command.call(Event.class, EventEndpoints.findByEventType(eventType, viewType));
    }

    @Override
    public Collection<IEvent> findSince(long timestamp) throws ServiceException
    {
        return findSince(timestamp, ViewType.Standard);
    }

    @Override
    public Collection<IEvent> findSince(long timestamp, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<DynamicEvent> command = new GetCollectionCommand<>(context);
        Collection<DynamicEvent> dynamicEvents = command.call(DynamicEvent.class, EventEndpoints.findSince(timestamp, viewType));

        Collection<IEvent> events = new ArrayList<>();
        for (DynamicEvent event : dynamicEvents)
        {
            IEvent newEvent = new Event();
            newEvent.setUniqueId(event.getUniqueId());
            newEvent.setUrn(event.getUrn());
            newEvent.setMoniker(event.getMoniker());
            if (event.getAccount() != null)
            {
                newEvent.setAccount(event.getAccount());
            }
            newEvent.setEventType(event.getEventType());
            if (event.getUser() != null)
            {
                newEvent.setUser(event.getUser());
            }
            if (event.getSource() != null)
            {
                newEvent.setSource(event.getSource().toString());
            }
            events.add(newEvent);
        }

        return events;
    }
}
