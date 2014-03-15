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

package com.snapbundle.client.tag;

import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.TagEndpoints;
import com.snapbundle.client.impl.AbstractCreateableBaseClient;
import com.snapbundle.model.context.ITag;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.Tag;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

class TagClient extends AbstractCreateableBaseClient<ITag> implements ITagClient
{
    TagClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ITag findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, TagEndpoints.findByUrn(urn, viewType), Tag.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, TagEndpoints.create());
    }

    @Override
    public void delete(ITag instance) throws ServiceException
    {

    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {

    }
}
