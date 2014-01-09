package com.snapbundle.client.endpoint;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.util.json.ViewType;

public class TagEndpoints implements ITagEndpoints
{
    public static String create()
    {
        return CREATE__PUT;
    }

    public static String delete()
    {
        return DELETE__DELETE;
    }

    public static String assign(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(ASSIGN__PUT, entityReferenceType, referenceUrn);
    }

    public static String findByTag(String tagName)
    {
        return findByTag(tagName, ViewType.Standard);
    }

    public static String findByTag(String tagName, ViewType viewType)
    {
        return String.format(FIND_SPECIFIC_TAG__GET, tagName, viewType);
    }

    public static String findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    public static String findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_ENTITY__GET, entityReferenceType, referenceUrn, viewType);
    }

    public static String findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName)
    {
        return findEntitiesByTagsAssignedToType(entityReferenceType, tagName, ViewType.Standard);
    }

    public static String findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName, ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_ASSIGNED_TO_TYPE__GET, entityReferenceType, tagName, viewType);
    }

    public static String findEntitiesByTagNameLike(String tagNameLike)
    {
        return findEntitiesByTagNameLike(tagNameLike, ViewType.Standard);
    }

    public static String findEntitiesByTagNameLike(String tagName, ViewType viewType)
    {
        return String.format(FIND_ENTITIES_BY_TAGS_NAMED_LIKE__GET, tagName, viewType);
    }
}
