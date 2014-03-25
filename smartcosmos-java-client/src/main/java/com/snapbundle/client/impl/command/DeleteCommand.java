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

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.base.AbstractBaseClient;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.JsonUtil;
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

public class DeleteCommand extends AbstractBaseClient implements ICommand<Object, Object>
{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteCommand.class);

    public DeleteCommand(ServerContext context)
    {
        super(context);
    }

    @Override
    public Object call(Class<?> clazz, String path) throws ServiceException
    {
        ClientResource service = createClient(path);

        try
        {
            Representation result = service.delete();

            if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
            {
                LOGGER.info("Successfully deleted {}", path);
            } else
            {
                JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
                JSONObject jsonResult = jsonRepresentation.getJsonObject();

                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                ResponseEntity response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return null;
    }

    @Override
    public Object call(Class<?> clazz, String path, JSONObject inputJson) throws ServiceException
    {
        throw new UnsupportedOperationException("DELETE command must not have inputJson");
    }

    @Override
    public Object call(Class<?> clazz, String path, JSONArray inputJson) throws ServiceException
    {
        throw new UnsupportedOperationException("DELETE command must not have inputJson");
    }
}

