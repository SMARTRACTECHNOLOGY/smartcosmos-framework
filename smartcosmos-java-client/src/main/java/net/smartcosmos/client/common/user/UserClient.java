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
package net.smartcosmos.client.common.user;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.command.PostCommand;
import net.smartcosmos.client.impl.endpoint.UserEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.context.User;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

class UserClient extends AbstractUpdateableBaseClient<IUser> implements IUserClient
{
    UserClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IUser findByEmailAddress(String emailAddress) throws ServiceException
    {
        return findByEmailAddress(emailAddress, ViewType.Standard);
    }

    @Override
    public IUser findByEmailAddress(String emailAddress, ViewType viewType) throws ServiceException
    {
        GetCommand<IUser> command = new GetCommand<>(context);
        return command.call(User.class, UserEndpoints.findByEmailAddress(emailAddress, viewType));
    }

    @Override
    public void changePassword(String emailAddress, String newPassword) throws ServiceException
    {
        Preconditions.checkNotNull(emailAddress);
        Preconditions.checkNotNull(newPassword);

        try
        {

            JSONObject jsonObject = new JSONObject()
                    .put(Field.EMAIL_ADDRESS_FIELD, emailAddress)
                    .put(Field.NEW_PASSWORD_FIELD, newPassword);

            PostCommand command = new PostCommand(context);
            command.call(Object.class, UserEndpoints.managePassword(), jsonObject);

        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void resetPassword(String emailAddress) throws ServiceException
    {
        Preconditions.checkNotNull(emailAddress);

        try
        {

            JSONObject jsonObject = new JSONObject()
                    .put(Field.EMAIL_ADDRESS_FIELD, emailAddress);

            PostCommand command = new PostCommand(context);
            command.call(Object.class, UserEndpoints.managePassword(), jsonObject);

        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public IUser findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, UserEndpoints.findByUrn(urn, viewType), User.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        try
        {
            return create(instance, UserEndpoints.create());
        } catch (ServiceException e)
        {
            return e.toResponseEntity();
        }
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, UserEndpoints.update());
    }
}
