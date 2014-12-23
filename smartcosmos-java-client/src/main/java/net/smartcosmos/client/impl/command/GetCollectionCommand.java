package net.smartcosmos.client.impl.command;

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

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractBaseClient;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GetCollectionCommand<T> extends AbstractBaseClient implements ICommand<Collection<T>, T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCollectionCommand.class);

    public GetCollectionCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public Collection<T> call(Class<? extends T> clazz, String path, JSONObject inputJson) throws ServiceException
    {
        throw new UnsupportedOperationException("GET command doesn't accept input JSON");
    }

    @Override
    public Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException
    {
        throw new UnsupportedOperationException("GET command doesn't accept input as a JSONArray");
    }

    @SuppressWarnings("checkstyle:emptyblock")
    @Override
    public Collection<T> call(Class<? extends T> clazz, String path) throws ServiceException
    {
        Collection<T> matches = new ArrayList<>();

        ClientResource service = createClient(path);

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
            {

                // No content - just let an empty array list be

            } else if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONArray jsonArray = jsonRepresentation.getJsonArray();
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject curObject = jsonArray.getJSONObject(i);
                    T instance = JsonUtil.fromJson(curObject, clazz);
                    matches.add(instance);
                }
            } else
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());

                throw new ServiceException(responseEntity);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return matches;
    }
}
