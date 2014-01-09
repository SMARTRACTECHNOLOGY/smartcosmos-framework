package com.snapbundle.client.endpoint;

public interface IInteractionEndpoints
{
    final String BASE = "/interactions";

    final String CREATE__PUT = BASE;

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String LIST_ALL__GET = BASE.concat("?view=%s");

    final String FIND_BY_DATA_LIKE__GET = BASE.concat("?dataLike=%s&view=%s");

    final String FIND_BY_OBJECT_URN_LIKE__GET = BASE.concat("?objectUrnLike=%s&view=%s");
}
