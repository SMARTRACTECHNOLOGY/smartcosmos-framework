/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.client.event;

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.endpoint.EventEndpoints;
import com.snapbundle.client.impl.base.AbstractFindableBaseClient;
import com.snapbundle.model.event.IEvent;
import com.snapbundle.pojo.event.Event;
import com.snapbundle.util.json.ViewType;

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
}
