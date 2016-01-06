package net.smartcosmos.client.impl.endpoint;

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


import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.util.json.ViewType;

public final class RelationshipEndpoints
{
    private RelationshipEndpoints()
    {
    }

    private static final String BASE = "/relationships";

    private static final String UPSERT_PUT = BASE.concat("/%s/%s");

    private static final String FIND_BY_URN_GET = BASE.concat("/%s?view=%s");

    private static final String FIND_ALL_BETWEEN_TWO_ENTITIES_GET = BASE.concat("/%s/%s/%s/%s?view=%s");

    private static final String FIND_SPECIFIC_RELATIONSHIP_GET = BASE.concat("/%s/%s/%s/%s/%s?view=%s");

    private static final String FIND_RELATIONSHIPS_GET = BASE.concat("/%s/%s/%s?reverse=false&view=%s");

    private static final String FIND_REVERSE_RELATIONSHIPS_GET = BASE.concat("/%s/%s/%s?reverse=true&view=%s");

    private static final String FIND_ALL_RELATIONSHIPS_GET = BASE.concat("/%s/%s?reverse=false&view=%s");

    private static final String DELETE_DELETE = BASE.concat("/%s");

    public static String upsert(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(UPSERT_PUT, entityReferenceType, referenceUrn);
    }

    public static String delete(String urn)
    {
        return String.format(urn);
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN_GET, urn, viewType);
    }

    public static String findAllBetweenTwoEntities(EntityReferenceType entityReferenceType,
                                                   String referenceUrn,
                                                   EntityReferenceType relatedEntityReferenceType,
                                                   String relatedReferenceUrn)
    {
        return findAllBetweenTwoEntities(entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                ViewType.Standard);
    }

    public static String findAllBetweenTwoEntities(EntityReferenceType entityReferenceType,
                                                   String referenceUrn,
                                                   EntityReferenceType relatedEntityReferenceType,
                                                   String relatedReferenceUrn,
                                                   ViewType viewType)
    {
        return String.format(FIND_ALL_BETWEEN_TWO_ENTITIES_GET,
                entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                viewType);
    }

    public static String findSpecificRelationship(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  EntityReferenceType relatedEntityReferenceType,
                                                  String relatedReferenceUrn,
                                                  String relationshipType)
    {
        return findSpecificRelationship(entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                relationshipType,
                ViewType.Standard);
    }

    public static String findSpecificRelationship(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  EntityReferenceType relatedEntityReferenceType,
                                                  String relatedReferenceUrn,
                                                  String relationshipType,
                                                  ViewType viewType)
    {
        return String.format(FIND_SPECIFIC_RELATIONSHIP_GET,
                entityReferenceType,
                referenceUrn,
                relatedEntityReferenceType,
                relatedReferenceUrn,
                relationshipType,
                viewType);
    }

    public static String findRelationships(EntityReferenceType entityReferenceType,
                                           String referenceUrn,
                                           String relationshipType)
    {
        return findRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    public static String findRelationships(EntityReferenceType entityReferenceType,
                                           String referenceUrn,
                                           ViewType viewType)
    {
        return String.format(FIND_ALL_RELATIONSHIPS_GET,
                entityReferenceType,
                referenceUrn,
                viewType);
    }

    public static String findRelationships(EntityReferenceType entityReferenceType,
                                           String referenceUrn,
                                           String relationshipType,
                                           ViewType viewType)
    {
        return String.format(FIND_RELATIONSHIPS_GET, entityReferenceType, referenceUrn, relationshipType, viewType);
    }

    public static String findReverseRelationships(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  String relationshipType)
    {
        return findReverseRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    public static String findReverseRelationships(EntityReferenceType entityReferenceType,
                                                  String referenceUrn,
                                                  String relationshipType,
                                                  ViewType viewType)
    {
        return String.format(FIND_REVERSE_RELATIONSHIPS_GET,
                entityReferenceType,
                referenceUrn,
                relationshipType,
                viewType);
    }

}
