package net.smartcosmos.client.impl.base;

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
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.client.impl.command.PostCommand;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractUpdateableBaseClient<T>
        extends AbstractCreateableBaseClient<T> implements IUpdateableBaseClient<T>
{
    protected AbstractUpdateableBaseClient(ServerContext context)
    {
        super(context);
    }

    public void update(T instance) throws ServiceException
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

    protected void update(JSONObject instance, String path) throws ServiceException
    {
        PostCommand command = new PostCommand(context);
        command.call(Object.class, path, instance);
    }
}
