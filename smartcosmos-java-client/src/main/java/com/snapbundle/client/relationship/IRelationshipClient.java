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

import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.IDeleteableBaseClient;
import com.snapbundle.client.impl.IUpsertableBaseClient;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IRelationship;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, deletes, or queries for {@link com.snapbundle.model.context.IRelationship} instances.
 * <p/>
 * A relationship is a <b>binary concept</b> that either exists, or doesn't. For example, a specific Driver object cannot
 * "LIKE" a specific Car object multiple times. The driver "LIKE"s the Car, or does not "LIKE" the car. As documented
 * by {@link com.snapbundle.client.impl.IUpsertableBaseClient}, relationship creation is idempotent; no matter how
 * many times the relationship is defined, it is guaranteed to only exist once in the database.
 */
public interface IRelationshipClient extends IUpsertableBaseClient<IRelationship>, IDeleteableBaseClient<IRelationship>
{
    /**
     * Retrieves <b>all</b> of the documented relationships between two specific entities.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn) throws ServiceException;

    /**
     * Retrieves <b>all</b> of the documented relationships between two specific entities.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using the specified field verbosity.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @param viewType                   Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>a very specific</b> relationship between two specific entities, if it exists.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>a very specific</b> relationship between two specific entities, if it exists.
     * <p/>
     * The {@link com.snapbundle.model.context.IRelationship} will be serialized using the specified field verbosity.
     *
     * @param entityReferenceType        owner/parent entity reference type
     * @param referenceUrn               owner/parent reference URN
     * @param relatedEntityReferenceType child entity reference type
     * @param relatedReferenceUrn        child entity reference URN
     * @param relationshipType           case-sensitive name of the relationship
     * @param viewType                   Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    IRelationship findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>all</b> child entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     * <p/>
     * One can use this query to answer the question "Tell me all of the relationships my Vehicle has, which might
     * be two: an {@link com.snapbundle.model.context.IObject} that is the "garage" where the vehicle is worked on and
     * another {@link com.snapbundle.model.context.IObject} that is the "owner" of the vehicle. Compare this result
     * with the collection returned from
     * {@link #findReverseRelationships(com.snapbundle.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType owner/parent entity reference type
     * @param referenceUrn        owner/parent reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>all</b> child entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using the specified field verbosity.
     * <p/>
     * One can use this query to answer the question "Tell me all of the relationships my Vehicle has, which might
     * be two: an {@link com.snapbundle.model.context.IObject} that is the "garage" where the vehicle is worked on and
     * another {@link com.snapbundle.model.context.IObject} that is the "owner" of the vehicle. Compare this result
     * with the collection returned from
     * {@link #findReverseRelationships(com.snapbundle.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType owner/parent entity reference type
     * @param referenceUrn        owner/parent reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @param viewType            Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;

    /**
     * Retrieves <b>all</b> owner/parent entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     * <p/>
     * One can use this query to answer the question "Tell me all of the parent relationships to my "garage", which might
     * be four: an {@link com.snapbundle.model.context.IObject} for each "Vehicle" that has been to the garage. Compare
     * this result with the collection returned from
     * {@link #findRelationships(com.snapbundle.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType child entity reference type
     * @param referenceUrn        child reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType) throws ServiceException;

    /**
     * Retrieves <b>all</b> owner/parent entities that the specified entity has the specified relationship type with.
     * <p/>
     * Each {@link com.snapbundle.model.context.IRelationship} will be serialized using the specified field verbosity.
     * <p/>
     * One can use this query to answer the question "Tell me all of the parent relationships to my "garage", which might
     * be four: an {@link com.snapbundle.model.context.IObject} for each "Vehicle" that has been to the garage. Compare
     * this result with the collection returned from
     * {@link #findRelationships(com.snapbundle.model.base.EntityReferenceType, String, String)}.
     *
     * @param entityReferenceType child entity reference type
     * @param referenceUrn        child reference URN
     * @param relationshipType    case-sensitive name of the relationship
     * @param viewType            Field verbosity
     * @return Non-null collection of matching relationships; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IRelationship> findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, String relationshipType, ViewType viewType) throws ServiceException;
}
