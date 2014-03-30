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
import com.snapbundle.client.impl.ICreateableBaseClient;
import com.snapbundle.client.impl.command.PutCommand;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractCreateableBaseClient<T> extends AbstractFindableBaseClient<T> implements ICreateableBaseClient<T>
{
    protected AbstractCreateableBaseClient(ServerContext context)
    {
        super(context);
    }

    public ResponseEntity create(T instance) throws ServiceException
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

    protected ResponseEntity create(JSONObject instance, String path) throws ServiceException
    {
        PutCommand<ResponseEntity> command = new PutCommand<>(context);
        return command.call(ResponseEntity.class, path, instance);
    }
}
