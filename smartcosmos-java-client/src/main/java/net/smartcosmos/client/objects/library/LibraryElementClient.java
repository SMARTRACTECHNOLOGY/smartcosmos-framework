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
package net.smartcosmos.client.objects.library;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.LibraryEndpoints;
import net.smartcosmos.objects.model.context.ILibraryElement;
import net.smartcosmos.objects.pojo.context.LibraryElement;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

class LibraryElementClient extends AbstractUpdateableBaseClient<ILibraryElement> implements ILibraryElementClient
{
    final Logger logger = LoggerFactory.getLogger(LibraryElementClient.class);

    LibraryElementClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ILibraryElement findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, LibraryEndpoints.findByUrn(urn, viewType), LibraryElement.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, LibraryEndpoints.create());
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, LibraryEndpoints.update());
    }


    @Override
    public Collection<ILibraryElement> query(LibraryEndpoints.Builder builder) throws ServiceException
    {
        GetCollectionCommand<ILibraryElement> command = new GetCollectionCommand<>(context);
        return command.call(LibraryElement.class, builder.build());
    }

    public ILibraryElement getParent(String libraryElementUrn) throws ServiceException
    {
        return getParent(libraryElementUrn, ViewType.Standard);
    }

    public ILibraryElement getParent(String libraryElementUrn, ViewType viewType) throws ServiceException
    {
        GetCommand<ILibraryElement> command = new GetCommand<>(context);
        return command.call(LibraryElement.class, LibraryEndpoints.getParent(libraryElementUrn, viewType));
    }

    public Collection<ILibraryElement> getChildren(String libraryElementUrn) throws ServiceException
    {
        return getChildren(libraryElementUrn, ViewType.Standard);
    }

    public Collection<ILibraryElement> getChildren(String libraryElementUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ILibraryElement> command = new GetCollectionCommand<>(context);
        return command.call(LibraryElement.class, LibraryEndpoints.getChildren(libraryElementUrn, viewType));
    }
}
