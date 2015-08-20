package net.smartcosmos.client.impl.endpoint;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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


import net.smartcosmos.model.event.EventType;
import net.smartcosmos.util.json.ViewType;

public final class EventEndpoints
{
    private EventEndpoints()
    {
    }

    private static final String BASE = "/events";

    private static final String CREATE_PUT = BASE;
    
    private static final String FIND_BY_URN_GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_EVENT_TYPE_GET = BASE.concat("?eventType=%s&view=%s");

    private static final String FIND_BY_TIMESTAMP_GET = BASE.concat("?timestamp=%s&view=%s");

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN_GET, urn, viewType);
    }

    public static String findByTimestamp(long timestamp)
    {
        return findSince(timestamp, ViewType.Standard);
    }

    public static String findSince(long timestamp, ViewType viewType)
    {
        return String.format(FIND_BY_TIMESTAMP_GET, timestamp, viewType);
    }

    public static String findByEventType(EventType eventType)
    {
        return findByEventType(eventType, ViewType.Standard);
    }

    public static String findByEventType(EventType eventType, ViewType viewType)
    {
        return String.format(FIND_BY_EVENT_TYPE_GET, eventType, viewType);
    }
    
    public static String create()
    {
        return CREATE_PUT;
    }
}

