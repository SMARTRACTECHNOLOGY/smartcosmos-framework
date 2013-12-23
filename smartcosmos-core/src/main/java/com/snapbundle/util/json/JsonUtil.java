package com.snapbundle.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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

    public static <T> T fromJson(String json, Class<T> entityClass)
    {
        T instance = null;

        try
        {
            instance = mapper.readValue(json, entityClass);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return instance;
    }

    public static <T> T fromJson(String json, Class<T> entityClass, JsonAutoDetect.Visibility visibility)
    {
        T instance = null;

        // Need to make this local to be thread safe
        ObjectMapper localMapper = new ObjectMapper();
        localMapper.setVisibility(PropertyAccessor.FIELD, visibility);

        try
        {
            instance = localMapper.readValue(json, entityClass);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return instance;
    }

    public static String toJson(Object object)
    {
        return toJson(object, JsonGenerationView.Standard.class);
    }

    public static String toJson(Object object, ViewType viewType)
    {
        return JsonUtil.toJson(object, viewType.getViewClass());
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

    public static String toJson(Object object, JsonAutoDetect.Visibility visibility)
    {
        String json = null;

        // Need to make this local to be thread safe
        ObjectMapper localMapper = new ObjectMapper();
        localMapper.setVisibility(PropertyAccessor.FIELD, visibility);

        try
        {
            json = localMapper.writeValueAsString(object);
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

