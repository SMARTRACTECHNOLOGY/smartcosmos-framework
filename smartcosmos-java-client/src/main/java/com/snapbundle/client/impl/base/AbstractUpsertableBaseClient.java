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

package com.snapbundle.client.impl.base;

import com.snapbundle.client.connectivity.ServerContext;
import com.snapbundle.client.connectivity.ServiceException;
import com.snapbundle.client.impl.IUpsertableBaseClient;
import com.snapbundle.client.impl.command.UpsertCommand;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

public abstract class AbstractUpsertableBaseClient<T> extends AbstractFindableBaseClient<T> implements IUpsertableBaseClient<T>
{
    protected AbstractUpsertableBaseClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ResponseEntity upsert(T instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            return upsert(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    protected ResponseEntity upsert(JSONObject instance, String path) throws ServiceException
    {
        UpsertCommand<ResponseEntity> command = new UpsertCommand<>(context);
        return command.call(ResponseEntity.class, path, instance);
    }

    protected Collection<ResponseEntity> upsert(JSONArray jsonArray, String path) throws ServiceException
    {
        UpsertCommand<ResponseEntity> command = new UpsertCommand<>(context);
        return command.call(path, jsonArray);
    }

    @Override
    public Collection<ResponseEntity> upsert(JSONArray jsonArray) throws ServiceException
    {
        throw new UnsupportedOperationException();
    }
}

