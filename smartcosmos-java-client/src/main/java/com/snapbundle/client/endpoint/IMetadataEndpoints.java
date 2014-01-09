package com.snapbundle.client.endpoint;

public interface IMetadataEndpoints
{
    final String BASE = "/metadata/%s/%s";

    final String UPSERT__PUT = BASE;

    final String FIND_SPECIFIC_KEY__GET = BASE.concat("?key=%s&view=%s");

    final String FINAL_ALL__GET = BASE.concat("?view=%s");

    final String DELETE__DELETE = BASE.concat("/%s");

    final String ENCODE_METADATA__POST = "/metadata/mapper/encode/%s";

    final String DECODE_METADATA__POST = "/metadata/mapper/decode/%s";
}
