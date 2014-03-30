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

package com.snapbundle.client.user;

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.connectivity.ServerContext;
import com.snapbundle.client.connectivity.ServiceException;
import com.snapbundle.client.impl.base.AbstractUpdateableBaseClient;
import com.snapbundle.client.impl.command.GetCommand;
import com.snapbundle.client.impl.command.PostCommand;
import com.snapbundle.client.impl.endpoint.UserEndpoints;
import com.snapbundle.model.context.IUser;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.User;
import com.snapbundle.util.json.ViewType;
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
