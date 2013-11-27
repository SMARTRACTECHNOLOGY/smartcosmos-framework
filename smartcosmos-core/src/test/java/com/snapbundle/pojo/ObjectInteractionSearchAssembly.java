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

package com.snapbundle.pojo;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.pojo.search.SearchClause;
import com.snapbundle.pojo.search.SearchCriteria;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Verbatim copy of the current server-side assembly logic included here for test purposes only
 * to help the developer visualize and debug the actual WHERE clause that is being generated from
 * their SearchCriteria class.
 */
public class ObjectInteractionSearchAssembly
{
    private static final Logger logger = Logger.getLogger(ObjectInteractionSearchAssembly.class.getName());

    protected SearchCriteria criteria;

    protected Map<String, Object> paramMap = new HashMap<>();

    protected StringBuilder whereClause = new StringBuilder();

    private boolean wasAssembled = false;

    public ObjectInteractionSearchAssembly(SearchCriteria criteria)
    {
        this.criteria = criteria;
    }

    public Map<String, Object> getParameterMap()
    {
        return paramMap;
    }

    public String getWhereClause()
    {
        return whereClause.toString();
    }

    public void assemble()
    {
        if (wasAssembled)
        {
            throw new IllegalStateException("WHERE clause was already assembled");
        }

        wasAssembled = true;


        int paramCount = 0;

        for (EntityReferenceType referenceType : criteria.getCriteriaMap().keySet())
        {
            SearchClause clause = criteria.getCriteriaMap().get(referenceType);

            if (whereClause.length() > 0)
            {
                whereClause.append(" AND ");
            }

            String paramName = "param" + (++paramCount);

            switch (referenceType)
            {
                case ObjectInteraction:
                    whereClause.append("m.");
                    break;
                case Object:
                    whereClause.append("m.object.");
                    break;
                case User:
                    whereClause.append("m.user.");
                    break;
                case Device:
                    whereClause.append("m.device.");
                    break;
                case ObjectInteractionSession:
                    whereClause.append("m.objectInteractionSession.");
                    break;
                default:
                    throw new IllegalStateException("Search criteria includes an unsupported entity reference type: " + referenceType.name());
            }

            whereClause.append(clause.field().getJpaFieldName())
                    .append(clause.predicate().getSymbol())
                    .append(":")
                    .append(paramName);

            switch (clause.predicate())
            {
                case EQUALS:
                    paramMap.put(paramName, clause.value().toString());
                    break;
                case STARTS_WITH:
                    paramMap.put(paramName, clause.value() + "%");
                    break;
                case BEFORE:
                case AFTER:
                    paramMap.put(paramName, clause.value());
            }
        }

        logger.info(whereClause.toString());
    }
}
