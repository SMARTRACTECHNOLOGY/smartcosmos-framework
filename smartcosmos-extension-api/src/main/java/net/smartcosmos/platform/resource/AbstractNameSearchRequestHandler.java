package net.smartcosmos.platform.resource;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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
import net.smartcosmos.Field;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.INamedObjectSearchDAO;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

public abstract class AbstractNameSearchRequestHandler<T extends IDomainResource>
        extends AbstractRequestHandler<String>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractNameSearchRequestHandler.class);

    private final Class<?> entity;

    private final Class<T> targetClass;

    private final EventType accessedEventType;

    private INamedObjectSearchDAO<T> dao;

    protected AbstractNameSearchRequestHandler(IContext context,
                                               INamedObjectSearchDAO<T> dao,
                                               Class<T> targetClass,
                                               Class<?> entity,
                                               EventType accessedEventType)
    {
        super(context);
        this.dao = dao;
        this.entity = entity;
        this.targetClass = targetClass;
        this.accessedEventType = accessedEventType;
    }

    @Override
    public Response handle(String nameLike, ViewType view, IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        Response response;

        Collection<T> matches = dao.findByNameLike(entity, nameLike, authenticatedUser.getAccount());

        if (matches.size() > 0)
        {
            IEventService eventSink = context.getServiceFactory().getEventService(authenticatedUser);

            JSONArray array = new JSONArray();

            for (T match : matches)
            {
                array.put(new JSONObject(JsonUtil.toJson(match, view)));

                //
                // EVENT: *Dynamic*
                //
                eventSink.recordEvent(accessedEventType, authenticatedUser.getAccount(), authenticatedUser, match);
            }

            response = Response
                    .ok(array.toString(3), MediaType.APPLICATION_JSON_TYPE)
                    .header(Field.HTTP_HEADER_EVENT, accessedEventType)
                    .build();
        } else
        {
            response = NO_CONTENT;
        }

        return response;
    }
}
