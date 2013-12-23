/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.search;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.util.json.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SearchCriteria
{
    private static Map<EntityReferenceType, List<EntityReferenceType>> supportedReferenceTypesMap = new HashMap<>();

    static
    {
        List<EntityReferenceType> objectInteractionSupportedTypes = new ArrayList<>();
        objectInteractionSupportedTypes.add(EntityReferenceType.ObjectInteraction);
        objectInteractionSupportedTypes.add(EntityReferenceType.Device);
        objectInteractionSupportedTypes.add(EntityReferenceType.Object);
        objectInteractionSupportedTypes.add(EntityReferenceType.ObjectInteractionSession);
        objectInteractionSupportedTypes.add(EntityReferenceType.User);

        supportedReferenceTypesMap.put(EntityReferenceType.ObjectInteraction, objectInteractionSupportedTypes);

        List<EntityReferenceType> eventSupportedType = new ArrayList<>();
        eventSupportedType.add(EntityReferenceType.Event);
        eventSupportedType.add(EntityReferenceType.User);

        supportedReferenceTypesMap.put(EntityReferenceType.Event, eventSupportedType);

        List<EntityReferenceType> userSupportedTypes = new ArrayList<>();
        userSupportedTypes.add(EntityReferenceType.User);
        userSupportedTypes.add(EntityReferenceType.Tag);
        userSupportedTypes.add(EntityReferenceType.Metadata);
        userSupportedTypes.add(EntityReferenceType.File);

        supportedReferenceTypesMap.put(EntityReferenceType.User, userSupportedTypes);

        List<EntityReferenceType> objectSupportedTypes = new ArrayList<>();
        objectSupportedTypes.add(EntityReferenceType.Object);
        objectSupportedTypes.add(EntityReferenceType.Tag);
        objectSupportedTypes.add(EntityReferenceType.Metadata);
        objectSupportedTypes.add(EntityReferenceType.File);

        supportedReferenceTypesMap.put(EntityReferenceType.Object, objectSupportedTypes);
    }

    protected EntityReferenceType target;

    protected Map<EntityReferenceType, SearchClause> criteriaMap = new HashMap<>();

    protected int limit;

    protected OrderBy orderBy;

    protected SearchCriteria()
    {
    }

    public SearchCriteria(EntityReferenceType target)
    {
        this.target = target;
    }

    public static SearchCriteria newInstance(EntityReferenceType entityReferenceType)
    {
        Preconditions.checkArgument(supportedReferenceTypesMap.containsKey(entityReferenceType),
                "EntityReferenceType is not supported as a primary search focus");
        return new SearchCriteria(entityReferenceType);
    }

    public static SearchCriteria fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.readValue(json, SearchCriteria.class);
    }

    public EntityReferenceType getTarget()
    {
        return target;
    }

    public Map<EntityReferenceType, SearchClause> getCriteriaMap()
    {
        return criteriaMap;
    }

    public int getLimit()
    {
        return limit;
    }

    public SearchCriteria setLimit(int limit)
    {
        this.limit = limit;
        return this;
    }

    public SearchCriteria addCriteria(EntityReferenceType entityReferenceType, SearchClause searchClause)
    {
        Preconditions.checkArgument(!criteriaMap.containsKey(entityReferenceType), "Search Criteria already contains a search clause for " + entityReferenceType.name());
        Preconditions.checkArgument(supportedReferenceTypesMap.get(target).contains(entityReferenceType), "Search Criteria doesn't support entity reference type " + entityReferenceType);

        criteriaMap.put(entityReferenceType, searchClause);
        return this;
    }

    public SearchCriteria setOrderBy(OrderBy orderBy)
    {
        this.orderBy = orderBy;
        return this;
    }

    public String toJson() throws JsonProcessingException
    {
        return JsonUtil.toJson(this, JsonAutoDetect.Visibility.ANY);
    }
}
