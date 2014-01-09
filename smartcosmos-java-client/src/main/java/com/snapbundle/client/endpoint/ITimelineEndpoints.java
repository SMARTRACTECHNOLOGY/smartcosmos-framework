package com.snapbundle.client.endpoint;

public interface ITimelineEndpoints
{
    /**
     * {entityReferenceType}/{referenceUrn}
     */
    final String BASE = "/timelines/%s/%s";

    final String CREATE__PUT = BASE;

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String FIND_BY_NAME_LIKE__GET = BASE.concat("?nameLike=%s&view=%s");

    final String UPDATE__PUT = BASE;
}

