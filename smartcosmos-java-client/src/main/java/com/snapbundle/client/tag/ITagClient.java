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

import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.IDeleteableBaseClient;
import com.snapbundle.client.impl.IUpsertableBaseClient;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.ITag;
import com.snapbundle.model.context.ITagAssignment;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.json.JSONArray;

import java.util.Collection;

/**
 * Upserts, deletes, or queries for {@link com.snapbundle.model.context.ITag} instances.
 */
public interface ITagClient extends IUpsertableBaseClient<ITag>, IDeleteableBaseClient<ITag>
{
    /**
     * Assigns the collection of tags to the specified entity indicated by the entity reference type and reference URN.
     * <p/>
     * <b>NOTE:</b> Use this method if you aren't sure what fields changed or if you aren't concerned about network
     * utilization.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param tags                Collection of tags to assign to the indicated entity
     * @return response entity indicating the success (or failure) of the assignment
     * @throws ServiceException
     */
    ResponseEntity assign(EntityReferenceType entityReferenceType, String referenceUrn, Collection<ITag> tags) throws ServiceException;

    /**
     * Assigns the collection of tags to the specified entity indicated by the entity reference type and reference URN.
     * <p/>
     * <b>NOTE:</b> Use this method if you are concerned about network utilization and only want to send the tag names.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param jsonArray           Collection of {@link com.snapbundle.model.context.ITag#getName()} values to assign to
     *                            the indicated entity
     * @return response entity indicating the success (or failure) of the assignment
     * @throws ServiceException
     */
    ResponseEntity assign(EntityReferenceType entityReferenceType, String referenceUrn, JSONArray jsonArray) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the tags
     * assigned to the specified entity indicated by the entity reference type and reference URN.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     * <p/>
     * Effectively this method would be used to tell you that, for example,
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} with
     * {@link com.snapbundle.model.context.IDevice#getUrn()} of <code>uuid:urn:system-assigned-uuid</code> has the
     * tags {"Foo", "Bar", "Baaq"} assigned to it.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the tags
     * assigned to the specified entity indicated by the entity reference type and reference URN.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using the specified field verbosity.
     * <p/>
     * Effectively this method would be used to tell you that, for example,
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} with
     * {@link com.snapbundle.model.context.IDevice#getUrn()} of <code>uuid:urn:system-assigned-uuid</code> has the
     * tags {"Foo", "Bar", "Baaq"} assigned to it.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param viewType            Field verbosity
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the
     * entities of the specified type that have been assigned such a tag.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     * <p/>
     * Effectively this method would be used to tell you that, for example, there are 13
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} instances that are assigned the tag <code>Foo</code>.
     *
     * @param entityReferenceType Entity reference type
     * @param tagName             Case-sensitive name of the tag
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the
     * entities of the specified type that have been assigned such a tag.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using the specified field verbosity.
     * <p/>
     * Effectively this method would be used to tell you that, for example, there are 13
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} instances that are assigned the tag <code>Foo</code>.
     *
     * @param entityReferenceType Entity reference type
     * @param tagName             Case-sensitive name of the tag
     * @param viewType            Field verbosity
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName, ViewType viewType) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the
     * entities that have been assigned such a tag.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     * <p/>
     * Effectively this method would be used to tell you that, for example, there are 13
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} instances that are assigned the tag <code>Foo</code>,
     * and 4 {@link com.snapbundle.model.context.IDevice} instances, and 2
     * {@link com.snapbundle.model.context.IObjectAddress} instances as well.
     *
     * @param tagName Case-sensitive name of the tag
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName) throws ServiceException;

    /**
     * Retrieves a collection of {@link com.snapbundle.model.context.ITagAssignment} records that represent the
     * entities that have been assigned such a tag.
     * <p/>
     * Each {@link com.snapbundle.model.context.ITagAssignment} will be serialized using the specified field verbosity.
     * <p/>
     * Effectively this method would be used to tell you that, for example, there are 13
     * {@link com.snapbundle.model.base.EntityReferenceType#Object} instances that are assigned the tag <code>Foo</code>,
     * and 4 {@link com.snapbundle.model.context.IDevice} instances, and 2
     * {@link com.snapbundle.model.context.IObjectAddress} instances as well.
     *
     * @param tagName  Case-sensitive name of the tag
     * @param viewType Field verbosity
     * @return Non-null collection of matching tag assignments; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName, ViewType viewType) throws ServiceException;

    /**
     * Find a specific {@link com.snapbundle.model.context.ITag#getName()} using a case-sensitive
     * exact match  using a {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param tagName Case-sensitive name of the tag
     * @return tag instance
     * @throws ServiceException
     */
    ITag findByTag(String tagName) throws ServiceException;

    /**
     * Find a specific {@link com.snapbundle.model.context.ITag#getName()} using a case-sensitive
     * exact match using the specified field verbosity.
     *
     * @param tagName  Case-sensitive name of the tag
     * @param viewType Field verbosity
     * @return tag instance
     * @throws ServiceException
     */
    ITag findByTag(String tagName, ViewType viewType) throws ServiceException;
}
