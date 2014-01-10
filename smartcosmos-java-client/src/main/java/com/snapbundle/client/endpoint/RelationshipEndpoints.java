package com.snapbundle.client.endpoint;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.RelationshipType;
import com.snapbundle.util.json.ViewType;

public final class RelationshipEndpoints
{
    private RelationshipEndpoints()
    {
    }

    private static final String BASE = "/relationships";

    private static final String UPSERT__PUT = BASE.concat("/%s/%s");

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_ALL_BETWEEN_TWO_ENTITIES__GET = BASE.concat("/%s/%s/%s/%s?view=%s");

    private static final String FIND_SPECIFIC_RELATIONSHIP__GET = BASE.concat("/%s/%s/%s/%s/%s?view=%s");

    private static final String FIND_RELATIONSHIPS__GET = BASE.concat("/%s/%s/%s?reverse=false&view=%s");

    private static final String FIND_REVERSE_RELATIONSHIPS__GET = BASE.concat("/%s/%s/%s?reverse=true&view=%s");

    private static final String DELETE__DELETE = BASE.concat("/%s");

    public static String upsert(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return String.format(UPSERT__PUT, entityReferenceType, referenceUrn);
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
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn)
    {
        return findAllBetweenTwoEntities(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, ViewType.Standard);
    }

    public static String findAllBetweenTwoEntities(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, ViewType viewType)
    {
        return String.format(FIND_ALL_BETWEEN_TWO_ENTITIES__GET, entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, viewType);
    }

    public static String findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, RelationshipType relationshipType)
    {
        return findSpecificRelationship(entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, relationshipType, ViewType.Standard);
    }

    public static String findSpecificRelationship(EntityReferenceType entityReferenceType, String referenceUrn, EntityReferenceType relatedEntityReferenceType, String relatedReferenceUrn, RelationshipType relationshipType, ViewType viewType)
    {
        return String.format(FIND_SPECIFIC_RELATIONSHIP__GET, entityReferenceType, referenceUrn, relatedEntityReferenceType, relatedReferenceUrn, relationshipType, viewType);
    }

    public static String findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, RelationshipType relationshipType)
    {
        return findRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    public static String findRelationships(EntityReferenceType entityReferenceType, String referenceUrn, RelationshipType relationshipType, ViewType viewType)
    {
        return String.format(FIND_RELATIONSHIPS__GET, entityReferenceType, referenceUrn, relationshipType, viewType);
    }

    public static String findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, RelationshipType relationshipType)
    {
        return findReverseRelationships(entityReferenceType, referenceUrn, relationshipType, ViewType.Standard);
    }

    public static String findReverseRelationships(EntityReferenceType entityReferenceType, String referenceUrn, RelationshipType relationshipType, ViewType viewType)
    {
        return String.format(FIND_REVERSE_RELATIONSHIPS__GET, entityReferenceType, referenceUrn, relationshipType, viewType);
    }

}
