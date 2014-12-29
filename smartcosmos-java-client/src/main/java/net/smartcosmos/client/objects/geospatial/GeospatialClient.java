package net.smartcosmos.client.objects.geospatial;

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
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.GeospatialEndpoints;
import net.smartcosmos.model.geo.IGeospatialEntry;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.geo.GeospatialEntry;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class GeospatialClient extends AbstractUpdateableBaseClient<IGeospatialEntry> implements IGeospatialClient
{
    GeospatialClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, GeospatialEndpoints.update());
    }

    @Override
    public IGeospatialEntry findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, GeospatialEndpoints.findByUrn(urn, viewType), GeospatialEntry.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, GeospatialEndpoints.create());
    }

    @Override
    public Collection<IGeospatialEntry> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IGeospatialEntry> command = new GetCollectionCommand<>(context);
        return command.call(GeospatialEntry.class, GeospatialEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public Collection<IGeospatialEntry> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
