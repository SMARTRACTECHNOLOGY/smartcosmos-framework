package com.snapbundle.client;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.MetadataDataType;
import com.snapbundle.model.context.RelationshipType;
import com.snapbundle.util.json.ViewType;

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

    public static final String PATH_CHANGE_MY_PASSWORD = "/account/password?setPassword=%s";

    public static String changePassword(String setPassword)
    {
        return String.format(PATH_CHANGE_MY_PASSWORD, setPassword);
    }

    public static final String PATH_RESET_MY_PASSWORD = "/account/password?resetPassword=true";

    public static String resetMyPassword()
    {
        return PATH_RESET_MY_PASSWORD;
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

//    GET     /relationships/{urn} (com.snapbundle.resource.secure.relationships.RelationshipResource)
//    DELETE  /relationships/{urn} (com.snapbundle.resource.secure.relationships.RelationshipResource)

//    GET     /relationships/{entityReferenceType}/{referenceUrn}/{relatedEntityReferenceType}/{relatedReferenceUrn} (com.snapbundle.resource.secure.relationships.RelationshipResource)
//    GET     /relationships/{entityReferenceType}/{referenceUrn}/{relatedEntityReferenceType}/{relatedReferenceUrn}/{relationshipType} (com.snapbundle.resource.secure.relationships.RelationshipResource)

    public static final String PATH_RELATIONSHIPS_URN = "/relationships/%s/%s";

    public static String relationships(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(PATH_RELATIONSHIPS_URN, entityReferenceType, referenceUrn);
    }

    public static final String PATH_QUERY_RELATIONSHIPS_URN = "/relationships/%s/%s/%s?reverse=%s";

    public static String queryRelationships(EntityReferenceType entityReferenceType, String referenceUrn, RelationshipType relationshipType, boolean reverse)
    {
        return String.format(PATH_QUERY_RELATIONSHIPS_URN, entityReferenceType, referenceUrn, relationshipType, reverse);
    }

    public static final String PATH_OBJECT_BY_OBJECT_URN = "/objects/object/%s?view=%s";

    public static String objectsByObjectUrn(String objectUrn, ViewType viewType)
    {
        return String.format(PATH_OBJECT_BY_OBJECT_URN, objectUrn, viewType.name());
    }

    public static final String PATH_QUERY_OBJECTS = "/objects?objectUrnLike=%s";

    public static String queryObjectsByObjectUrnLike(String objectUrnLike)
    {
        return String.format(PATH_QUERY_OBJECTS, objectUrnLike);
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

    public static final String PATH_QUERY_TAGS_BY_NAME_ONLY = "/tags?tagName=%s";

    public static String queryTagsByTagName(String tagName)
    {
        return String.format(PATH_QUERY_TAGS_BY_NAME_ONLY, tagName);
    }

    public static final String PATH_QUERY_TAGS_BY_TYPE_BY_NAME = "/tags?entityReferenceType=%s&tagName=%s";

    public static String queryTagsByTypeByName(EntityReferenceType entityReferenceType, String tagName)
    {
        return String.format(PATH_QUERY_TAGS_BY_TYPE_BY_NAME, entityReferenceType, tagName);
    }

    public static final String PATH_QUERY_TAGS_BY_REFERENTIAL = "/tags?entityReferenceType=%s&referenceUrn=%s";

    public static String queryTagsByReferential(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(PATH_QUERY_TAGS_BY_REFERENTIAL, entityReferenceType, referenceUrn);
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
