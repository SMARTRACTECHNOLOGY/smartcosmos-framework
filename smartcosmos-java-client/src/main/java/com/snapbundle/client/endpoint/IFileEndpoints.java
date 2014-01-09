package com.snapbundle.client.endpoint;

public interface IFileEndpoints
{
    final String BASE = "/files";

    final String CREATE__PUT = BASE;

    final String DELETE__DELETE = BASE.concat("/%s");

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String RETRIEVE_FILE_CONTENT__GET = BASE.concat("/%s/contents");

    final String LIST_OWNED_BY__GET = BASE.concat("/%s/%s?view=%s");

    /**
     * MULTIPART_FORM_DATA with a file stream and filename content disposition.
     */
    final String UPLOAD_FILE_CONTENT__MULTIPART__POST = BASE.concat("/%s/multipart");

    /**
     * application/octet-stream
     */
    final String UPLOAD_FILE_CONTENT__OCTET_STREAM__POST = BASE.concat("/%s/octet");

}
