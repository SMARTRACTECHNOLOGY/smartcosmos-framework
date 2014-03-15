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

package com.snapbundle.client.relationship;

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.endpoint.RelationshipEndpoints;
import com.snapbundle.client.impl.AbstractUpsertableBaseClient;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.client.impl.command.GetCommand;
import com.snapbundle.client.impl.command.PostCommand;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.pojo.context.Relationship;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

import static com.snapbundle.Field.ENTITY_REFERENCE_TYPE;
import static com.snapbundle.Field.REFERENCE_URN_FIELD;
import static com.snapbundle.Field.URN_FIELD;

class RelationshipClient extends AbstractUpsertableBaseClient<IRelationship> implements IRelationshipClient
{
    RelationshipClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(URN_FIELD));

        try
        {
            PostCommand command = new PostCommand(context);
            command.call(Object.class, RelationshipEndpoints.delete(instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void upsert(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(ENTITY_REFERENCE_TYPE));
        Preconditions.checkState(instance.has(REFERENCE_URN_FIELD));

        try
        {
            EntityReferenceType ert = EntityReferenceType.valueOf(instance.getString(ENTITY_REFERENCE_TYPE));
            upsert(instance, RelationshipEndpoints.upsert(ert, instance.getString(REFERENCE_URN_FIELD)));
        } catch (IllegalArgumentException | JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(IRelationship instance) throws ServiceException
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
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context);
        return command.call(Relationship.class, RelationshipEndpoints.findAllBetweenTwoEntities(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, viewType));
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        GetCommand<IRelationship> command = new GetCommand<>(context);
        return command.call(Relationship.class, RelationshipEndpoints.findSpecificRelationship(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, relationshipType, viewType));
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context);
        return command.call(Relationship.class, RelationshipEndpoints.findRelationships(entityReferenceType, referenceUrn, relationshipType, viewType));
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context);
        return command.call(Relationship.class, RelationshipEndpoints.findReverseRelationships(entityReferenceType, referenceUrn, relationshipType, viewType));
    }

    @Override
    public IRelationship findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, RelationshipEndpoints.findByUrn(urn, viewType), Relationship.class);
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType) throws ServiceException
    {
        return findSpecificRelationship(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, relationshipType, ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException
    {
        return findRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException
    {
        return findReverseRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn) throws ServiceException
    {
        return findAllBetweenTwoEntities(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, ViewType.Standard);
    }
}
