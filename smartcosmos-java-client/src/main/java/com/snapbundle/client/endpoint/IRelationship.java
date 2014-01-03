package com.snapbundle.client.endpoint;

public interface IRelationship
{
    final String BASE = "/relationships";

    final String UPSERT__PUT = BASE.concat("/%s/%s");

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String FIND_ALL_BETWEEN_TWO_ENTITIES__GET = BASE.concat("/%s/%s/%s/%s?view=%s");

    final String FIND_SPECIFIC_RELATIONSHIP__GET = BASE.concat("/%s/%s/%s/%s/%s?view=%s");

    final String FIND_RELATIONSHIPS__GET = BASE.concat("/%s/%s/%s?reverse=false&view=%s");

    final String FIND_REVERSE_RELATIONSHIPS__GET = BASE.concat("/%s/%s/%s?reverse=true&view=%s");

    final String DELETE__DELETE = BASE;
}
