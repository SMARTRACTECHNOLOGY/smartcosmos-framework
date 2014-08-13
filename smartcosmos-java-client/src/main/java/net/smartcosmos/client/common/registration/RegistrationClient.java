/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.client.common.registration;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractBaseClient;
import net.smartcosmos.client.impl.endpoint.RegistrationEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

final class RegistrationClient extends AbstractBaseClient implements IRegistrationClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(RegistrationClient.class);

    public RegistrationClient()
    {
        super(new ServerContext());
    }

    RegistrationClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public boolean isRealmAvailable(String realm) throws ServiceException
    {
        boolean isAvailable = false;
        ClientResource service = createClient(RegistrationEndpoints.checkRealmAvailability(realm));

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();
            ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

            if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            } else
            {
                isAvailable = (responseEntity.getCode() == Result.OK.getCode());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return isAvailable;
    }

    @Override
    public RegistrationResponse register(String emailAddress) throws ServiceException
    {
        return register(emailAddress, null);
    }

    @Override
    public RegistrationResponse register(String emailAddress, String realm) throws ServiceException
    {
        RegistrationResponse response = null;

        LOGGER.info("Attempting to register a new account under email address " + emailAddress);

        try
        {
            JSONObject jsonObject = new JSONObject()
                    .put(Field.EMAIL_ADDRESS_FIELD, emailAddress);

            if (realm != null)
            {
                jsonObject.put(Field.REALM_FIELD, realm);
            }

            JsonRepresentation sndJsonRepresentation = new JsonRepresentation(jsonObject.toString());

            ClientResource service = createClient(RegistrationEndpoints.registration());
            Representation result = service.post(sndJsonRepresentation);

            if (service.getStatus().equals(Status.CLIENT_ERROR_CONFLICT) ||
                    service.getStatus().equals(Status.CLIENT_ERROR_BAD_REQUEST))
            {
                // Email address or Realm is already registered
                JsonRepresentation rcvJsonRepresentation = new JsonRepresentation(result);
                JSONObject jsonResult = rcvJsonRepresentation.getJsonObject();
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                throw new ServiceException(responseEntity);
            } else if (service.getStatus().equals(Status.SUCCESS_CREATED))
            {
                JsonRepresentation rcvJsonRepresentation = new JsonRepresentation(result);
                JSONObject jsonResult = rcvJsonRepresentation.getJsonObject();
                response = new RegistrationResponse(jsonResult);
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(service.getStatus().getCode());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return response;
    }
}
