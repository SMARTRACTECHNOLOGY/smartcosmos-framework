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

import com.snapbundle.client.IBaseClient;
import com.snapbundle.client.ServiceException;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

public interface IRelationshipClient extends IBaseClient<IRelationship>
{
    /**
     * Inserts a new or updates an existing record if the URNs and entity types in the relationship definition already
     * have an existing relationship.
     * <p/>
     * It is not possible to define multiple identical relationships where only the timestamps differ.
     *
     * @param instance
     */
    void upsert(IRelationship instance) throws ServiceException;

    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn) throws ServiceException;

    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType) throws ServiceException;

    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType) throws ServiceException;

    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    /**
     * Removes an existing relationship, if it exists.
     *
     * @param relationship
     * @throws ServiceException
     */
    void delete(IRelationship relationship) throws ServiceException;
}
