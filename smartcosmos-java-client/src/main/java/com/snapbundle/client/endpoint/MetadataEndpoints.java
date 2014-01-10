package com.snapbundle.client.endpoint;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.MetadataDataType;
import com.snapbundle.util.json.ViewType;

public final class MetadataEndpoints
{
    private MetadataEndpoints()
    {
    }

    private static final String BASE = "/metadata/%s/%s";

    private static final String UPSERT__PUT = BASE;

    private static final String FIND_SPECIFIC_KEY__GET = BASE.concat("?key=%s&view=%s");

    private static final String FINAL_ALL__GET = BASE.concat("?view=%s");

    private static final String DELETE__DELETE = BASE.concat("/%s");

    private static final String ENCODE_METADATA__POST = "/metadata/mapper/encode/%s";

    private static final String DECODE_METADATA__POST = "/metadata/mapper/decode/%s";

    public static String upsert(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(UPSERT__PUT, entityReferenceType, referenceUrn);
    }

    public static String delete(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(DELETE__DELETE, entityReferenceType, referenceUrn);
    }

    public static String findAll(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return findAll(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    public static String findAll(EntityReferenceType entityReferenceType, String referenceUrn, ViewType view)
    {
        return String.format(FINAL_ALL__GET, entityReferenceType, referenceUrn, view);
    }

    public static String findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key)
    {
        return findSpecificKey(entityReferenceType, referenceUrn, key, ViewType.Standard);
    }

    public static String findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key, ViewType view)
    {
        return String.format(FIND_SPECIFIC_KEY__GET, entityReferenceType, referenceUrn, key, view);
    }

    public static String encodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(ENCODE_METADATA__POST, metadataDataType);
    }

    public static String decodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(DECODE_METADATA__POST, metadataDataType);
    }

}
