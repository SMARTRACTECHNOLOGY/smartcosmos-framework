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

package com.snapbundle.client.object.address;

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.endpoint.ObjectAddressEndpoints;
import com.snapbundle.client.impl.base.AbstractUpdateableBaseClient;
import com.snapbundle.client.impl.command.DeleteCommand;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.client.impl.command.GetCommand;
import com.snapbundle.client.impl.command.PostCommand;
import com.snapbundle.model.context.IObjectAddress;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.ObjectAddress;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static com.snapbundle.Field.OBJECT_URN_FIELD;
import static com.snapbundle.Field.TYPE_FIELD;
import static com.snapbundle.Field.URN_FIELD;

class ObjectAddressClient extends AbstractUpdateableBaseClient<IObjectAddress> implements IObjectAddressClient
{
    ObjectAddressClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public void delete(IObjectAddress instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            delete(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(URN_FIELD));
        Preconditions.checkState(instance.has(OBJECT_URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context);
            command.call(ObjectAddress.class, ObjectAddressEndpoints.delete(instance.getString(Field.OBJECT_URN_FIELD),
                    instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<IObjectAddress> findLastN(String objectUrn, int count, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectAddress> command = new GetCollectionCommand<>(context);
        return command.call(ObjectAddress.class, ObjectAddressEndpoints.findLast(objectUrn, count, viewType));
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(URN_FIELD));
        Preconditions.checkState(instance.has(OBJECT_URN_FIELD));

        try
        {
            PostCommand command = new PostCommand(context);
            command.call(ObjectAddress.class,
                    ObjectAddressEndpoints.update(instance.getString(OBJECT_URN_FIELD), instance.getString(URN_FIELD)),
                    instance);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(OBJECT_URN_FIELD));
        Preconditions.checkState(instance.has(TYPE_FIELD));

        try
        {
            return create(instance, ObjectAddressEndpoints.create(instance.getString(OBJECT_URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public IObjectAddress findByUrn(String objectUrn, String urn, ViewType viewType) throws ServiceException
    {
        GetCommand<IObjectAddress> command = new GetCommand<>(context);
        return command.call(ObjectAddress.class, ObjectAddressEndpoints.findByUrn(objectUrn, urn, viewType));
    }

    @Override
    public Collection<IObjectAddress> findLastN(String objectUrn, int count) throws ServiceException
    {
        return findLastN(objectUrn, count, ViewType.Standard);
    }

    @Override
    public IObjectAddress findByUrn(String objectUrn, String urn) throws ServiceException
    {
        return findByUrn(objectUrn, urn, ViewType.Standard);
    }

    @Override
    public IObjectAddress findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        throw new UnsupportedOperationException("Please use findByUrn(String objectUrn, String urn)");
    }
}
