package net.smartcosmos.platform.resource;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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

import com.fasterxml.jackson.core.JsonProcessingException;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IBaseDAO;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static net.smartcosmos.Field.HTTP_HEADER_EVENT;

public abstract class AbstractFindByUrnRequestHandler<T extends IDomainResource>
        extends AbstractRequestHandler<String>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractFindByUrnRequestHandler.class);

    private final Class<?> entity;

    private final EventType accessedEventType;

    private IBaseDAO<T> dao;

    protected AbstractFindByUrnRequestHandler(IContext context,
                                              IBaseDAO<T> dao,
                                              Class<?> entity,
                                              EventType accessedEventType)
    {
        super(context);
        this.dao = dao;
        this.entity = entity;
        this.accessedEventType = accessedEventType;
    }

    @Override
    public Response handle(String urn, ViewType view, IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        Response response;

        T target = dao.findByUrn(entity, urn, authenticatedUser.getAccount());

        if (target != null)
        {
            //
            // EVENT: *Dynamic*
            //
            IEventService eventSink = context.getServiceFactory().getEventService(authenticatedUser);
            eventSink.recordEvent(accessedEventType, authenticatedUser.getAccount(), authenticatedUser, target);

            response = Response
                    .ok()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(JsonUtil.toJson(target, view))
                    .header(HTTP_HEADER_EVENT, accessedEventType)
                    .cacheControl(createStandardCacheControl())
                    .tag(createETag(target))
                    .build();
        } else
        {
            response = NO_CONTENT;
        }

        return response;
    }
}
