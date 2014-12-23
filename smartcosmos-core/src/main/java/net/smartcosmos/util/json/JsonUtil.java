package net.smartcosmos.util.json;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import net.smartcosmos.Field;
import net.smartcosmos.model.event.IEvent;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Collection of type-safe JSON serialization and de-serialization utilities.
 */
public final class JsonUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil()
    {
    }

    static
    {
        MAPPER.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

    }

    public static <T> T fromJson(JSONObject jsonObject, Class<T> entityClass)
    {
        return JsonUtil.fromJson(jsonObject.toString(), entityClass);
    }

    public static <T> T fromJson(String json, Class<T> entityClass)
    {
        T instance = null;

        try
        {
            instance = MAPPER.readValue(json, entityClass);
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

    public static JSONObject translateEvent(IEvent event) throws JSONException
    {
        JSONObject curEvent = new JSONObject()
                .put(Field.URN_FIELD, event.getUrn())
                .put(Field.EVENT_TYPE, event.getEventType())
                .put(Field.LAST_MODIFIED_TIMESTAMP_FIELD, event.getLastModifiedTimestamp());

        if (event.getUser() != null)
        {
            curEvent.put(Field.USER_FIELD, new JSONObject(toJson(event.getUser(), ViewType.Minimum)));
        } else
        {
            curEvent.put(Field.USER_FIELD, (String) null);
        }

        if (event.getSource() != null)
        {
            curEvent.put(Field.SOURCE_FIELD, new JSONObject(event.getSource()));
        } else
        {
            curEvent.put(Field.SOURCE_FIELD, (String) null);
        }

        return curEvent;
    }

    public static String toJson(Object object)
    {
        return toJson(object, JsonGenerationView.Standard.class);
    }

    public static String toJson(Object object, ViewType viewType)
    {
        return toJson(object, viewType.getViewClass());
    }

    public static String toJson(Object object, Class<? extends JsonGenerationView.Published> viewClass)
    {
        String json = null;

        Preconditions.checkArgument((viewClass != null), "The viewClass must not be null");
        Preconditions.checkArgument((object != null), "The object must not be null");

        try
        {
            json = MAPPER.writerWithView(viewClass).writeValueAsString(object);
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
