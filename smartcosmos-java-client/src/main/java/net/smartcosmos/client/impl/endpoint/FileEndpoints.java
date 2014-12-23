package net.smartcosmos.client.impl.endpoint;


import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.util.json.ViewType;

public final class FileEndpoints
{
    private FileEndpoints()
    {
    }

    private static final String BASE = "/files";

    private static final String CREATE__PUT = BASE;

    private static final String DELETE__DELETE = BASE.concat("/%s");

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String RETRIEVE_FILE_CONTENT__GET = BASE.concat("/%s/contents");

    private static final String LIST_OWNED_BY__GET = BASE.concat("/%s/%s?view=%s");

    /**
     * MULTIPART_FORM_DATA with a file stream and filename content disposition.
     */
    private static final String UPLOAD_FILE_CONTENT__MULTIPART__POST = BASE.concat("/%s/multipart");

    /**
     * application/octet-stream
     */
    private static final String UPLOAD_FILE_CONTENT__OCTET_STREAM__POST = BASE.concat("/%s/octet");

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
