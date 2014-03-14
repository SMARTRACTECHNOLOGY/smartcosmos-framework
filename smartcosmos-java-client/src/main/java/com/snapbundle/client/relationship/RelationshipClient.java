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

import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class RelationshipClient implements IRelationshipClient
{
    private final ServerContext context;

    RelationshipClient(ServerContext context)
    {
        this.context = context;
    }

    @Override
    public void upsert(IRelationship instance) throws ServiceException
    {

    }

    @Override
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn) throws ServiceException
    {
        return null;
    }

    @Override
    public Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType) throws ServiceException
    {
        return null;
    }

    @Override
    public IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException
    {
        return null;
    }

    @Override
    public Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException
    {
        return null;
    }

    @Override
    public Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public void delete(IRelationship relationship) throws ServiceException
    {

    }

    @Override
    public IRelationship findByUrn(String urn) throws ServiceException
    {
        return null;
    }

    @Override
    public IRelationship findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return null;
    }

    @Override
    public ResponseEntity create(IRelationship instance) throws ServiceException
    {
        return null;
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return null;
    }
}
