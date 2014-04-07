/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.client.impl.endpoint;

import com.snapbundle.model.event.EventType;
import com.snapbundle.util.json.ViewType;

public final class EventEndpoints
{
    private EventEndpoints()
    {
    }

    private static final String BASE = "/events";

    private static final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    private static final String FIND_BY_EVENT_TYPE__GET = BASE.concat("?eventType=%s&view=%s");

    private static final String FIND_BY_TIMESTAMP__GET = BASE.concat("?timestamp=%s&view=%s");

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByTimestamp(long timestamp)
    {
        return findSince(timestamp, ViewType.Standard);
    }

    public static String findSince(long timestamp, ViewType viewType)
    {
        return String.format(FIND_BY_TIMESTAMP__GET, timestamp, viewType);
    }

    public static String findByEventType(EventType eventType)
    {
        return findByEventType(eventType, ViewType.Standard);
    }

    public static String findByEventType(EventType eventType, ViewType viewType)
    {
        return String.format(FIND_BY_EVENT_TYPE__GET, eventType, viewType);
    }
}

