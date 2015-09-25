package net.smartcosmos.client.objects.relationship;

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

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.endpoint.RelationshipEndpoints;
import net.smartcosmos.client.impl.base.AbstractUpsertableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.Field;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.objects.pojo.context.Relationship;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;


class RelationshipClient extends AbstractUpsertableBaseClient<IRelationship> implements IRelationshipClient
{
    RelationshipClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ResponseEntity upsert(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(Field.ENTITY_REFERENCE_TYPE));
        Preconditions.checkState(instance.has(Field.REFERENCE_URN_FIELD));

        try
        {
            EntityReferenceType ert = EntityReferenceType.valueOf(instance.getString(Field.ENTITY_REFERENCE_TYPE));
            return upsert(instance, RelationshipEndpoints.upsert(ert, instance.getString(Field.REFERENCE_URN_FIELD)));
        } catch (IllegalArgumentException | JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(Field.URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context, getClient());
            command.call(Object.class, RelationshipEndpoints.delete(instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
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
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType,
                                                               String referenceUrn,
                                                               EntityReferenceType relatedEntityReferenceType,
                                                               String relatedReferenceUrn,
                                                               ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context, getClient());
        return command.call(Relationship.class, RelationshipEndpoints.findAllBetweenTwoEntities(entityReferenceType,
                referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, viewType));
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  EntityReferenceType relatedEntityReferenceType,
                                                  String relatedReferenceUrn,
                                                  String relationshipType,
                                                  ViewType viewType) throws ServiceException
    {
        GetCommand<IRelationship> command = new GetCommand<>(context, getClient());
        return command.call(Relationship.class,
                RelationshipEndpoints.findSpecificRelationship(entityReferenceType,
                        referenceUrn,
                        relatedEntityReferenceType,
                        relatedReferenceUrn,
                        relationshipType,
                        viewType));
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType,
                                                       String referenceUrn,
                                                       String relationshipType,
                                                       ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context, getClient());
        return command.call(Relationship.class,
                RelationshipEndpoints.findRelationships(entityReferenceType, referenceUrn, relationshipType, viewType));
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType,
                                                              String referenceUrn,
                                                              String relationshipType,
                                                              ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IRelationship> command = new GetCollectionCommand<>(context, getClient());
        return command.call(Relationship.class,
                RelationshipEndpoints.findReverseRelationships(entityReferenceType,
                        referenceUrn,
                        relationshipType,
                        viewType));
    }

    @Override
    public IRelationship findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, RelationshipEndpoints.findByUrn(urn, viewType), Relationship.class);
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  EntityReferenceType relatedEntityReferenceType,
                                                  String relatedReferenceUrn,
                                                  String relationshipType) throws ServiceException
    {
        return findSpecificRelationship(entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                relationshipType,
                ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType,
                                                       String referenceUrn,
                                                       String relationshipType) throws ServiceException
    {
        return findRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType,
                                                              String referenceUrn,
                                                              String relationshipType) throws ServiceException
    {
        return findReverseRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    @Override
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType,
                                                               String referenceUrn,
                                                               EntityReferenceType relatedEntityReferenceType,
                                                               String relatedReferenceUrn) throws ServiceException
    {
        return findAllBetweenTwoEntities(entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                ViewType.Standard);
    }
}
