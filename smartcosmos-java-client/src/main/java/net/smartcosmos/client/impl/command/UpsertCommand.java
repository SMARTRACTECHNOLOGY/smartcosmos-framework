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

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractBaseClient;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Client;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UpsertCommand<T> extends AbstractBaseClient implements ICommand<T, T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UpsertCommand.class);

    public UpsertCommand(final ServerContext context, final Client client)
    {
        super(context, client);
    }

    @Override
    public T call(final Class<? extends T> clazz, final String path) throws ServiceException
    {
        throw new UnsupportedOperationException("UPSERT command must have inputJson");
    }

    @Override
    public Collection<ResponseEntity> call(final String path, final JSONArray inputJson) throws ServiceException
    {
        Collection<ResponseEntity> responses = new ArrayList<>();

        Preconditions.checkNotNull(inputJson);

        ClientResource service = createClient(path);

        try
        {
            Representation result = service.put(new JsonRepresentation(inputJson));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONArray jsonArray = jsonRepresentation.getJsonArray();

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    ResponseEntity response = JsonUtil.fromJson(jsonArray.getJSONObject(i), ResponseEntity.class);
                    responses.add(response);
                }
            } else
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                throwServiceException(jsonResult);
            }
        } catch (JSONException | IOException | ResourceException e)
        {
            throwServiceException(service, e);
        }

        return responses;
    }

    @Override
    public T call(final Class<? extends T> clazz, final String path, final JSONObject inputJson) throws ServiceException
    {
        T response;

        Preconditions.checkNotNull(inputJson);

        ClientResource service = createClient(path);

        try
        {
            Representation result = service.put(new JsonRepresentation(inputJson));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();

            response = JsonUtil.fromJson(jsonResult, clazz);

            if (service.getStatus().equals(Status.SUCCESS_CREATED) || service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.debug(((ResponseEntity) response).getMessage());
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                throwServiceException(jsonResult);
            }

        } catch (JSONException | IOException | ResourceException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }
}
