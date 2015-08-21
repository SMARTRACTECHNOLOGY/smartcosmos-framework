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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class TagEndpoints
{
    private TagEndpoints()
    {
    }

    private static final String ENCODING = "UTF-8";

    private static final String BASE = "/tags";

    private static final String UPSERT_PUT = BASE;

    private static final String ASSIGN_PUT = BASE.concat("/%s/%s");

    private static final String REVOKE_ASSIGNMENT_DELETE = BASE.concat("/tag/%s/%s/%s");

    private static final String FIND_BY_URN_GET = BASE.concat("/%s?view=%s");

    private static final String FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_ENTITY_GET
            = BASE.concat("?entityReferenceType=%s&referenceUrn=%s&view=%s");

    private static final String FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_TYPE_GET
            = BASE.concat("?entityReferenceType=%s&tagName=%s&view=%s");

    private static final String FIND_ENTITIES_BY_TAGS_NAMED_LIKE_GET
            = BASE.concat("?tagName=%s&view=%s");

    private static final String FIND_SPECIFIC_TAG_GET = BASE.concat("/tag/%s?view=%s");

    private static final String DELETE_DELETE = BASE.concat("/tag/%s");

    public static String upsert()
    {
        return UPSERT_PUT;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN_GET, urn, viewType);
    }

    public static String delete(String urn)
    {
        return String.format(DELETE_DELETE, urn);
    }

    public static String assign(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(ASSIGN_PUT, entityReferenceType, referenceUrn);
    }

    public static String revokeAssignment(EntityReferenceType entityReferenceType, String referenceUrn, String tagName)
    {
        String encodedTagName = null;
        try
        {
            encodedTagName = URLEncoder.encode(tagName, ENCODING);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return String.format(REVOKE_ASSIGNMENT_DELETE, encodedTagName, entityReferenceType, referenceUrn);
    }

    public static String findByTag(String tagName)
    {
        return findByTag(tagName, ViewType.Standard);
    }

    public static String findByTag(String tagName, ViewType viewType)
    {
        String encodedTagName = null;
        try
        {
            encodedTagName = URLEncoder.encode(tagName, ENCODING);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return String.format(FIND_SPECIFIC_TAG_GET, encodedTagName, viewType);
    }

    public static String findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType,
                                                            String referenceUrn)
    {
        return findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    public static String findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType,
                                                            String referenceUrn,
                                                            ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_ENTITY_GET,
                entityReferenceType,
                referenceUrn,
                viewType);
    }

    public static String findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName)
    {
        return findEntitiesByTagsAssignedToType(entityReferenceType, tagName, ViewType.Standard);
    }

    public static String findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType,
                                                          String tagName,
                                                          ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_TYPE_GET, entityReferenceType, tagName, viewType);
    }

    public static String findEntitiesByTagNameLike(String tagNameLike)
    {
        return findEntitiesByTagNameLike(tagNameLike, ViewType.Standard);
    }

    public static String findEntitiesByTagNameLike(String tagName, ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_NAMED_LIKE_GET, tagName, viewType);
    }
}