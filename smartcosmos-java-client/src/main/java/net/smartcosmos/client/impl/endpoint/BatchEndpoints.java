package net.smartcosmos.client.impl.endpoint;

import net.smartcosmos.util.json.ViewType;

public class BatchEndpoints
{
    private BatchEndpoints()
    {
    }

    private static final String BASE = "/batch";

    private static final String CREATE__PUT = BASE;

    private static final String UPDATE__POST = BASE;

    private static final String FIND_BY_TRANSMISSION_URN__GET = BASE.concat("/%s?view=%s");

    public static String fileTransmissionRequest()
    {
        return CREATE__PUT;
    }

    public static String fileTransmissionReceipt()
    {
        return UPDATE__POST;
    }

    public static String transmissionStatus(String urn)
    {
        return transmissionStatus(urn, ViewType.Standard);
    }

    public static String transmissionStatus(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_TRANSMISSION_URN__GET, urn, viewType);
    }
}
