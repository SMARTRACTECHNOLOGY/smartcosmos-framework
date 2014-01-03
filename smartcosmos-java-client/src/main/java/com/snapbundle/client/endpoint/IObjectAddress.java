package com.snapbundle.client.endpoint;

public interface IObjectAddress
{
    final String BASE = "/objects/object/%s/address";

    final String CREATE__PUT = BASE;

    final String UPDATE__POST = BASE;

    final String FIND_LAST_N__GET = BASE.concat("?cound=%s&view=%s");

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String DELETE__DELETE = BASE;
}
