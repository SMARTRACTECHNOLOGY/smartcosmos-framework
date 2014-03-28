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

package com.snapbundle.client.impl.command;

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.base.AbstractBaseClient;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.base.Result;
import com.snapbundle.util.json.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.snapbundle.Field.CODE_FIELD;
import static com.snapbundle.Field.MESSAGE_FIELD;

public class PutCommand<T> extends AbstractBaseClient implements ICommand<T, T>
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PutCommand.class);

    public PutCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public T call(Class<? extends T> clazz, String path) throws ServiceException
    {
        throw new UnsupportedOperationException("PUT command must have inputJson");
    }

    @Override
    public T call(Class<? extends T> clazz, String path, JSONObject inputJson) throws ServiceException
    {
        T response;

        Preconditions.checkNotNull(inputJson);

        ClientResource service = createClient(path);

        try
        {
            JSONObject jsonResult;
            try
            {
                Representation result = service.put(new JsonRepresentation(inputJson));
                JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
                jsonResult = jsonRepresentation.getJsonObject();

                response = JsonUtil.fromJson(jsonResult, clazz);

            } catch (ResourceException e)
            {
                if (e.getStatus().equals(Status.CLIENT_ERROR_CONFLICT))
                {
                    ResponseEntity entity = new ResponseEntity.Builder(
                            Result.ERR_ALREADY_EXISTS.getCode(),
                            String.format(Result.ERR_ALREADY_EXISTS.getFormattedMessage(), "user", inputJson.getString(Field.EMAIL_ADDRESS_FIELD)))
                            .build();

                    throw new ServiceException(entity);
                } else
                {
                    LOGGER.error("Unexpected Resource Exception", e);
                    throw new ServiceException(e);
                }
            }

            if (!service.getStatus().equals(Status.SUCCESS_CREATED))
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
            } else
            {
                LOGGER.debug(((ResponseEntity) response).getMessage());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public T call(Class<? extends T> clazz, String path, JSONArray inputJson) throws ServiceException
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

            if (!service.getStatus().equals(Status.SUCCESS_CREATED))
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
            } else
            {
                LOGGER.debug(((ResponseEntity) response).getMessage());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }
}

