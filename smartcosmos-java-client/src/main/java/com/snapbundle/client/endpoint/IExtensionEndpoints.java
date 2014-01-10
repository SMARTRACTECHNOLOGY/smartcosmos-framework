package com.snapbundle.client.endpoint;

public interface IExtensionEndpoints
{
    final String BASE = "/extensions";

    final String CREATE__PUT = BASE;

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String DELETE__DELETE = BASE.concat("/%s");

    final String FIND_BY_NAME_LIKE__GET = BASE.concat("?nameLike=%s&view=%s");

    final String UPDATE__POST = BASE;
}
