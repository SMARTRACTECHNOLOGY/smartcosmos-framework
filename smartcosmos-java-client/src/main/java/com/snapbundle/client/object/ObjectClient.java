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

package com.snapbundle.client.object;

import com.snapbundle.Field;
import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.client.endpoint.ObjectEndpoints;
import com.snapbundle.client.impl.AbstractClient;
import com.snapbundle.model.context.IObject;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.ObjectImpl;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
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

class ObjectClient extends AbstractClient implements IObjectClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(ObjectClient.class);

    private final ServerContext context;

    ObjectClient(ServerContext context)
    {
        this.context = context;
    }

    @Override
    public IObject findByUrn(String urn) throws ServiceException
    {
        return findByUrn(urn, ViewType.Standard);
    }

    @Override
    public IObject findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        IObject object = null;

        ClientResource service = createClient(ObjectEndpoints.findByUrn(urn, viewType), context);

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
            {
                LOGGER.info("No matching object with URN " + urn);
            } else if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                object = JsonUtil.fromJson(jsonResult, ObjectImpl.class);
            } else
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return object;
    }

    @Override
    public ResponseEntity create(IObject instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            return create(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        ResponseEntity response;

        ClientResource service = createClient(ObjectEndpoints.create(), context);

        try
        {
            Representation result = service.put(new JsonRepresentation(instance));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();

            response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

            if (!service.getStatus().equals(Status.SUCCESS_CREATED))
            {
                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(response);
            } else
            {
                LOGGER.debug(response.getMessage());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public void update(IObject instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            update(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        ClientResource service = createClient(ObjectEndpoints.update(), context);

        try
        {
            Representation result = service.post(new JsonRepresentation(instance));

            if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
            {
                LOGGER.info("Updated object with urn " + instance.getString(Field.URN_FIELD));
            } else
            {
                JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
                JSONObject jsonResult = jsonRepresentation.getJsonObject();

                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                ResponseEntity response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn) throws ServiceException
    {
        return findByExactObjectUrn(objectUrn, ViewType.Standard);
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn, ViewType viewType) throws ServiceException
    {
        IObject object = null;

        ClientResource service = createClient(ObjectEndpoints.findByExactObjectUrn(objectUrn, viewType), context);

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (service.getStatus().equals(Status.SUCCESS_NO_CONTENT))
            {
                LOGGER.info("No matching object with an object URN " + objectUrn);
            } else if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                object = JsonUtil.fromJson(jsonResult, ObjectImpl.class);

            } else if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return object;
    }

    @Override
    public Collection<IObject> query(ObjectEndpoints.Builder builder) throws ServiceException
    {
        Collection<IObject> matches = new ArrayList<>();

        ClientResource service = createClient(builder.build(), context);

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);

            if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                JSONObject jsonResult = jsonRepresentation.getJsonObject();
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            } else
            {
                JSONArray jsonArray = jsonRepresentation.getJsonArray();
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject curObject = jsonArray.getJSONObject(i);
                    IObject object = JsonUtil.fromJson(curObject, ObjectImpl.class);
                    matches.add(object);
                }
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return matches;
    }
}
