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
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.util.json.ViewType;

public final class MetadataEndpoints
{
    private MetadataEndpoints()
    {
    }

    private static final String BASE = "/metadata/%s/%s";

    private static final String UPSERT_PUT = BASE;

    private static final String FIND_BY_URN_GET = BASE.concat("/%s?view=%s");

    private static final String FIND_SPECIFIC_KEY_GET = BASE.concat("?key=%s&view=%s");

    private static final String FINAL_ALL_GET = BASE.concat("?view=%s");

    private static final String DELETE_DELETE = BASE.concat("/%s");

    private static final String ENCODE_METADATA_POST = "/metadata/mapper/encode/%s";

    private static final String DECODE_METADATA_POST = "/metadata/mapper/decode/%s";

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN_GET, urn, viewType);
    }

    public static String upsert(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(UPSERT_PUT, entityReferenceType, referenceUrn);
    }

    public static String delete(EntityReferenceType entityReferenceType, String referenceUrn, String key)
    {
        return String.format(DELETE_DELETE, entityReferenceType, referenceUrn, key);
    }

    public static String findAll(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return findAll(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    public static String findAll(EntityReferenceType entityReferenceType, String referenceUrn, ViewType view)
    {
        return String.format(FINAL_ALL_GET, entityReferenceType, referenceUrn, view);
    }

    public static String findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key)
    {
        return findSpecificKey(entityReferenceType, referenceUrn, key, ViewType.Standard);
    }

    public static String findSpecificKey(EntityReferenceType entityReferenceType,
                                         String referenceUrn,
                                         String key,
                                         ViewType view)
    {
        return String.format(FIND_SPECIFIC_KEY_GET, entityReferenceType, referenceUrn, key, view);
    }

    public static String encodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(ENCODE_METADATA_POST, metadataDataType);
    }

    public static String decodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(DECODE_METADATA_POST, metadataDataType);
    }

}
