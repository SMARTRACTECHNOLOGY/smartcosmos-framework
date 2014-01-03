package com.snapbundle.client.endpoint;

public interface ITag
{
    final String BASE = "/tags";

    final String CREATE__PUT = BASE;

    final String ASSIGN__PUT = BASE.concat("/%s/%s");

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String FIND_BY_TAG__GET = BASE.concat("?tagName=%s&view=%s");

    final String FIND_ASSIGNED_TO_ENTITY__GET = BASE.concat("?entityReferenceType=%s&referenceUrn=%s&view=%s");

    final String FIND_ASSIGNED_TO_TYPE__GET = BASE.concat("?entityReferenceType=%s&tagName=%s&view=%s");

    final String FIND_SPECIFIC_TAG__GET = BASE.concat("/tag/%s?view=%s");

    final String DELETE__DELETE = BASE.concat("/tag/%s");
}
