package com.snapbundle.client.endpoint;

public interface IObjectEndpoints
{
    final String BASE = "/objects";

    final String CREATE__PUT = BASE;

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String UPDATE__POST = BASE;

    final String QUERY_TYPE_AGNOSTIC__GET = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&objectUrnLike=%s&view=%s");

    final String QUERY_TYPE_SPECIFIC__GET = BASE.concat("?modifiedAfter=%s&monikerLike=%s&nameLike=%s&objectUrnLike=%s&objectType=%s&view=%s");

    final String FIND_BY_OBJECT_URN__GET = BASE.concat("/object/%s?exact=%s&view=%s");
}
