package com.snapbundle.client.endpoint;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.util.json.ViewType;

public class FileEndpoints implements IFileEndpoints
{
    public static String create()
    {
        return CREATE__PUT;
    }

    public static String delete(String urn)
    {
        return String.format(DELETE__DELETE, urn);
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String retrieveContents(String urn)
    {
        return String.format(RETRIEVE_FILE_CONTENT__GET, urn);
    }

    public static String uploadContentsAsMultipart(String urn)
    {
        return String.format(UPLOAD_FILE_CONTENT__MULTIPART__POST, urn);
    }

    public static String uploadContentsAsOctetStream(String urn)
    {
        return String.format(UPLOAD_FILE_CONTENT__OCTET_STREAM__POST, urn);
    }

    public static String listFilesOwnedByEntity(EntityReferenceType entityReferenceType, String referenceUrn)
    {
        return listFilesOwnedByEntity(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    public static String listFilesOwnedByEntity(EntityReferenceType entityReferenceType, String referenceUrn, ViewType view)
    {
        return String.format(LIST_OWNED_BY__GET, entityReferenceType, referenceUrn, view);
    }
}
