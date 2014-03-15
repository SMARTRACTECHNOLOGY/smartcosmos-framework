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

package com.snapbundle.client.metadata;

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.MetadataEndpoints;
import com.snapbundle.client.impl.AbstractUpsertableBaseClient;
import com.snapbundle.model.context.IMetadata;
import com.snapbundle.pojo.context.Metadata;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

class MetadataClient extends AbstractUpsertableBaseClient<IMetadata> implements IMetadataClient
{
    MetadataClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IMetadata findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, viewType, MetadataEndpoints.findByUrn(urn, viewType), Metadata.class);
    }

    @Override
    public void upsert(JSONObject instance) throws ServiceException
    {

    }
}
