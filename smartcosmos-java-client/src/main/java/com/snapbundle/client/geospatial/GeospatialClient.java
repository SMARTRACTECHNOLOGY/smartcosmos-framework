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

package com.snapbundle.client.geospatial;

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.GeospatialEndpoints;
import com.snapbundle.client.impl.AbstractUpdateableBaseClient;
import com.snapbundle.model.geo.IGeospatialEntry;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.geo.GeospatialEntry;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

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
        return findByUrn(urn, viewType, GeospatialEndpoints.findByUrn(urn, viewType), GeospatialEntry.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, GeospatialEndpoints.create());
    }
}
