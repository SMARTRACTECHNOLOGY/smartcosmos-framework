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

package com.snapbundle.client.account;

import com.snapbundle.Field;
import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.client.endpoint.AccountEndpoints;
import com.snapbundle.client.impl.AbstractClient;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.base.Result;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class AccountClient extends AbstractClient implements IAccountClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);

    private final ServerContext context;

    AccountClient(ServerContext context)
    {
        this.context = context;
    }

    @Override
    public IAccount fetch() throws ServiceException
    {
        return fetch(ViewType.Standard);
    }

    @Override
    public IAccount fetch(ViewType viewType) throws ServiceException
    {
        IAccount account;
        ClientResource service = createClient(AccountEndpoints.view(viewType), context);

        try
        {
            Representation result = service.get();
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();

            if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            } else
            {
                account = JsonUtil.fromJson(jsonResult, Account.class);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return account;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) throws ServiceException
    {
        boolean wasChanged;

        ClientResource service = createClient(AccountEndpoints.changeMyPassword(), context);

        try
        {
            JSONObject jsonObject = new JSONObject()
                    .put(Field.OLD_PASSWORD_FIELD, oldPassword)
                    .put(Field.NEW_PASSWORD_FIELD, newPassword);

            Representation result = service.post(new JsonRepresentation(jsonObject));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();

            ResponseEntity responseEntity = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

            if (!service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.error("Unexpected HTTP status code returned: " + service.getStatus().getCode());
                throw new ServiceException(responseEntity);
            } else
            {
                LOGGER.debug(responseEntity.getMessage());
                wasChanged = (responseEntity.getCode() == Result.OK.getCode());
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected exception", e);
            throw new ServiceException(e);
        }

        return wasChanged;
    }

    @Override
    public void resetPassword(String emailAddress) throws ServiceException
    {

    }
}
