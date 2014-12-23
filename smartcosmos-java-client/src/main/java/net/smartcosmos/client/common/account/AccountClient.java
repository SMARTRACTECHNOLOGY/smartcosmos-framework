package net.smartcosmos.client.common.account;

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
import net.smartcosmos.client.impl.base.AbstractFindableBaseClient;
import net.smartcosmos.client.impl.endpoint.AccountEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class AccountClient extends AbstractFindableBaseClient<IAccount> implements IAccountClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(AccountClient.class);

    AccountClient(ServerContext context)
    {
        super(context);
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
        ClientResource service = createClient(AccountEndpoints.view(viewType));

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

        ClientResource service = createClient(AccountEndpoints.changeMyPassword());

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
        // TODO: reset password routine
    }

    @Override
    public IAccount findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        throw new UnsupportedOperationException("Method not available as part of the SDK");
    }
}
