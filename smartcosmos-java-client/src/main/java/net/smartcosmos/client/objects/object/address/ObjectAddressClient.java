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

package net.smartcosmos.client.objects.object.address;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.command.PostCommand;
import net.smartcosmos.client.impl.endpoint.ObjectAddressEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.objects.pojo.context.ObjectAddress;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static net.smartcosmos.Field.OBJECT_URN_FIELD;
import static net.smartcosmos.Field.TYPE_FIELD;
import static net.smartcosmos.Field.URN_FIELD;


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
        try
        {
            if (instance.has(Field.OBJECT_FIELD))
            {
                JSONObject object = instance.getJSONObject(Field.OBJECT_FIELD);
                instance.put(OBJECT_URN_FIELD, object.getString(OBJECT_URN_FIELD));
            }

            Preconditions.checkState(instance.has(URN_FIELD));
            Preconditions.checkState(instance.has(OBJECT_URN_FIELD));

            DeleteCommand command = new DeleteCommand(context);
            command.call(ObjectAddress.class, ObjectAddressEndpoints.delete(instance.getString(OBJECT_URN_FIELD),
                    instance.getString(URN_FIELD)));
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
        try
        {
            if (instance.has(Field.OBJECT_FIELD))
            {
                JSONObject object = instance.getJSONObject(Field.OBJECT_FIELD);
                instance.put(OBJECT_URN_FIELD, object.getString(OBJECT_URN_FIELD));
            }

            Preconditions.checkState(instance.has(URN_FIELD));
            Preconditions.checkState(instance.has(OBJECT_URN_FIELD));

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
        try
        {
            if (instance.has(Field.OBJECT_FIELD))
            {
                JSONObject object = instance.getJSONObject(Field.OBJECT_FIELD);
                instance.put(OBJECT_URN_FIELD, object.getString(OBJECT_URN_FIELD));
            }

            Preconditions.checkState(instance.has(OBJECT_URN_FIELD));
            Preconditions.checkState(instance.has(TYPE_FIELD));

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
