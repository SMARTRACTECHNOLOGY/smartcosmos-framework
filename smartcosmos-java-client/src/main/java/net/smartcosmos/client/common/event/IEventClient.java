package net.smartcosmos.client.common.event;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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
