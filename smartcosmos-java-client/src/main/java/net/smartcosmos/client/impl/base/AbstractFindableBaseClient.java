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
import net.smartcosmos.client.impl.IFindableBaseClient;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.util.json.ViewType;

public abstract class AbstractFindableBaseClient<T> extends AbstractBaseClient implements IFindableBaseClient<T>
{
    protected AbstractFindableBaseClient(final ServerContext context)
    {
        super(context);
    }

    public T findByUrn(final String urn) throws ServiceException
    {
        return findByUrn(urn, ViewType.Standard);
    }

    protected T findByUrn(final String urn, final String path, final Class<? extends T> clazz) throws ServiceException
    {
        GetCommand<T> command = new GetCommand<>(context, getClient());
        return command.call(clazz, path);
    }
}
