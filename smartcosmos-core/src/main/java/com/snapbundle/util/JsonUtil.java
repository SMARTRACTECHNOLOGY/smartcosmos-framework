package com.snapbundle.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil()
    {
    }

    static
    {
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

    }

    public static String toJson(Object object)
    {
        return JsonUtil.toJson(object, JsonGenerationView.Standard.class);
    }

    public static String toJson(Object object, Class<? extends JsonGenerationView.Published> viewClass)
    {
        String json = null;

        Preconditions.checkArgument((viewClass != null), "The viewClass must not be null");
        Preconditions.checkArgument((object != null), "The object must not be null");

        try
        {
            json = mapper.writerWithView(viewClass).writeValueAsString(object);
        } catch (JsonProcessingException e)
        {
            if (LOG.isDebugEnabled())
            {
                e.printStackTrace();
            }

            LOG.error("Unable to convert object to JSON: {}", e.getMessage());
        }

        return json;
    }
}

