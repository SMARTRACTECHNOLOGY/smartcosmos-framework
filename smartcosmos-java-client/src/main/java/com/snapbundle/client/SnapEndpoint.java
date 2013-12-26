package com.snapbundle.client;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.MetadataDataType;

public final class SnapEndpoint
{
    //    public static final String PATH_REALM_CHECK = "/public/realm/%s";
    public static final String PATH_REALM_CHECK = "/registration/realm/%s";


    public static String realmCheck(String realm)
    {
        return String.format(PATH_REALM_CHECK, realm);
    }

    //    public static final String PATH_REGISTRATION = "/public/registration/register";
    public static final String PATH_REGISTRATION = "/registration/register";

    public static String registration()
    {
        return PATH_REGISTRATION;
    }

    public static final String PATH_CHANGE_PASSWORD = "/app/password";

    public static String changePassword()
    {
        return PATH_CHANGE_PASSWORD;
    }

    public static final String PATH_DEVICES = "/devices";

    public static String devices()
    {
        return PATH_DEVICES;
    }

    public static final String PATH_OBJECTS = "/objects";

    public static String objects()
    {
        return PATH_OBJECTS;
    }

    public static final String PATH_ENCODE_METADATA = "/metadata/mapper/encode/%s";

    public static String encodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(PATH_ENCODE_METADATA, metadataDataType);
    }

    public static final String PATH_ASSIGN_METADATA = "/metadata/%s/%s";

    public static String assignMetadata(EntityReferenceType entityReferenceType, String urn)
    {
        return String.format(PATH_ASSIGN_METADATA, entityReferenceType, urn);
    }

    public static final String PATH_QUERY_METADATA = "/metadata/%s/%s";

    public static String queryMetadata(EntityReferenceType entityReferenceType, String urn)
    {
        return String.format(PATH_QUERY_METADATA, entityReferenceType, urn);
    }

    public static final String PATH_DECODE_METADATA = "/metadata/mapper/decode/%s";

    public static String decodeMetadata(MetadataDataType metadataDataType)
    {
        return String.format(PATH_DECODE_METADATA, metadataDataType);
    }

    public static final String PATH_ASSIGN_TAGS = "/tags/%s/%s";

    public static String tags(EntityReferenceType entityReferenceType, String urn)
    {
        return String.format(PATH_ASSIGN_TAGS, entityReferenceType, urn);
    }

    public static final String PATH_OBJECT_INTERACTION = "/interactions";

    public static String interactions()
    {
        return PATH_OBJECT_INTERACTION;
    }

    public static final String PATH_FILES = "/files";

    public static String files()
    {
        return PATH_FILES;
    }

    public static final String PATH_UPLOAD_FILE = "/files/%s";

    public static String uploadFile(String fileUrn)
    {
        return String.format(PATH_UPLOAD_FILE, fileUrn);
    }

    private SnapEndpoint()
    {
    }
}
