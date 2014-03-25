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

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.endpoint.TagEndpoints;
import com.snapbundle.client.impl.base.AbstractUpsertableBaseClient;
import com.snapbundle.client.impl.command.DeleteCommand;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.client.impl.command.GetCommand;
import com.snapbundle.client.impl.command.PutCommand;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.ITag;
import com.snapbundle.model.context.ITagAssignment;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.Tag;
import com.snapbundle.pojo.context.TagAssignment;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static com.snapbundle.Field.URN_FIELD;

class TagClient extends AbstractUpsertableBaseClient<ITag> implements ITagClient
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
    public ResponseEntity upsert(JSONObject instance) throws ServiceException
    {
        return upsert(instance, TagEndpoints.upsert());
    }

    @Override
    public void delete(ITag instance) throws ServiceException
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
        Preconditions.checkState(instance.has(URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context);
            command.call(Object.class, TagEndpoints.delete(instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public ResponseEntity assign(EntityReferenceType entityReferenceType, String referenceUrn, Collection<ITag> tags) throws ServiceException
    {
        JSONArray array = new JSONArray();

        for (ITag tag : tags)
        {
            try
            {
                JSONObject jsonObject = new JSONObject()
                        .put(Field.NAME_FIELD, tag.getName());

                array.put(jsonObject);
            } catch (JSONException e)
            {
                throw new ServiceException(e);
            }
        }

        return assign(entityReferenceType, referenceUrn, array);
    }

    @Override
    public ResponseEntity assign(EntityReferenceType entityReferenceType, String referenceUrn, JSONArray jsonArray) throws ServiceException
    {
        // PUT
        PutCommand<ResponseEntity> command = new PutCommand<>(context);
        return command.call(ResponseEntity.class, TagEndpoints.assign(entityReferenceType, referenceUrn), jsonArray);
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, viewType));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagsAssignedToType(entityReferenceType, tagName, viewType));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagNameLike(tagName, viewType));
    }

    @Override
    public ITag findByTag(String tagName, ViewType viewType) throws ServiceException
    {
        GetCommand<ITag> command = new GetCommand<>(context);
        return command.call(Tag.class, TagEndpoints.findByTag(tagName, viewType));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName) throws ServiceException
    {
        return findEntitiesByTagsAssignedToType(entityReferenceType, tagName, ViewType.Standard);
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName) throws ServiceException
    {
        return findEntitiesByTagNameLike(tagName, ViewType.Standard);
    }

    @Override
    public ITag findByTag(String tagName) throws ServiceException
    {
        return findByTag(tagName, ViewType.Standard);
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException
    {
        return findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, ViewType.Standard);
    }
}
