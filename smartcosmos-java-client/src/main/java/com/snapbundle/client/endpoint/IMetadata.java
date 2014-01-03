package com.snapbundle.client.endpoint;

public interface IMetadata
{
    final String BASE = "/metadata/%s/%s";

    final String UPSERT__PUT = BASE;

    final String FIND_SPECIFIC_KEY__GET = BASE.concat("?key=%s&view=%s");

    final String FINAL_ALL__GET = BASE.concat("?view=%s");

    final String DELETE__DELETE = BASE.concat("/%s");
}
