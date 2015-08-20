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
import net.smartcosmos.pojo.base.Result;
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

import static net.smartcosmos.Field.CODE_FIELD;
import static net.smartcosmos.Field.MESSAGE_FIELD;

public class UpsertCommand<T> extends AbstractBaseClient implements ICommand<T, T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UpsertCommand.class);

    public UpsertCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public T call(Class<? extends T> clazz, String path) throws ServiceException
    {
        throw new UnsupportedOperationException("UPSERT command must have inputJson");
    }

    @Override
    public Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException
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

                try
                {
                    if (jsonResult.has(CODE_FIELD) && jsonResult.has(MESSAGE_FIELD))
                    {
                        ResponseEntity responseEntity = new ResponseEntity();
                        responseEntity.setCode(jsonResult.getInt(CODE_FIELD));
                        responseEntity.setMessage(jsonResult.getString(MESSAGE_FIELD));

                        throw new ServiceException(responseEntity);

                    } else if (jsonResult.has(CODE_FIELD))
                    {
                        throw new ServiceException(jsonResult.getInt(CODE_FIELD));
                    }
                } catch (JSONException e)
                {
                    throw new ServiceException(Result.ERR_FAILURE.getCode());
                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return responses;
    }

    @Override
    public T call(Class<? extends T> clazz, String path, JSONObject inputJson) throws ServiceException
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

                try
                {
                    if (jsonResult.has(CODE_FIELD) && jsonResult.has(MESSAGE_FIELD))
                    {
                        ResponseEntity responseEntity = new ResponseEntity();
                        responseEntity.setCode(jsonResult.getInt(CODE_FIELD));
                        responseEntity.setMessage(jsonResult.getString(MESSAGE_FIELD));

                        throw new ServiceException(responseEntity);

                    } else if (jsonResult.has(CODE_FIELD))
                    {
                        throw new ServiceException(jsonResult.getInt(CODE_FIELD));
                    }
                } catch (JSONException e)
                {
                    throw new ServiceException(Result.ERR_FAILURE.getCode());
                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }
}


