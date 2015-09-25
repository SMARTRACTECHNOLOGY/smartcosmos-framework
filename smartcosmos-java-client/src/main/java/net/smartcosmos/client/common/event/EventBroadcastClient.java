package net.smartcosmos.client.common.event;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.EventEndpoints;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.event.Event;
import net.smartcosmos.util.json.ViewType;

public class EventBroadcastClient extends AbstractUpdateableBaseClient<IEvent> implements IEventBroadcastClient
{
   EventBroadcastClient(ServerContext context) 
   {
      super(context);
   }
   
   @Override
   public IEvent findByUrn(String urn, ViewType viewType) throws ServiceException 
   {
      return findByUrn(urn, EventEndpoints.findByUrn(urn, viewType), Event.class);
   }

   @Override
   public void update(JSONObject instance) throws ServiceException 
   {
      throw new UnsupportedOperationException("Method not available as part of the SDK");
   }

   @Override
   public ResponseEntity create(JSONObject instance) throws ServiceException 
   {
      ResponseEntity response = create(instance, EventEndpoints.create());
      return response;
   }

   @Override
   public Collection<IEvent> findByEventType(EventType eventType) throws ServiceException 
   {
      return findByEventType(eventType, ViewType.Standard);   
   }

   @Override
   public Collection<IEvent> findByEventType(EventType eventType, ViewType viewType) throws ServiceException 
   {
        GetCollectionCommand<IEvent> command = new GetCollectionCommand<>(context, getClient());
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
        GetCollectionCommand<DynamicEvent> command = new GetCollectionCommand<>(context, getClient());
        Collection<DynamicEvent> dynamicEvents
                = command.call(DynamicEvent.class, EventEndpoints.findSince(timestamp, viewType));

        Collection<IEvent> events = new ArrayList<>();
        for (DynamicEvent event : dynamicEvents)
        {
            IEvent newEvent = new Event();
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
