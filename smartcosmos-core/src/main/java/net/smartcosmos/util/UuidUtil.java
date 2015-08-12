package net.smartcosmos.util;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 *
 * Motiviated by:
 *
 * https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/
 *
 * Created by tcross on 23/06/15.
 */
public final class UuidUtil
{

    private UuidUtil()
    {
        // only static methods - no public constructor
    };

    /**
     *
     * @return a time-based UUID, reorganized so that the time-based bits are at the front, , i.e., a bunch of UUIDs
     * coming from the same server are more or less sequential in the first four bytes, for better insertion speeds.
     *
     * @see <a href="https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/">
     *     https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/
     *     </a>
     *
     */
    public static UUID getUuid()
    {
        String baseUuidString = Generators.timeBasedGenerator().generate().toString();
        String[] parts = baseUuidString.split("-");
        String sortedUuidString = new StringBuilder(36).
                append(parts[2]).
                append(parts[1]).
                append("-").
                append(parts[0].substring(0, 4)).
                append("-").
                append(parts[0].substring(4, 8)).
                append("-").
                append(parts[3]).
                append("-").
                append(parts[4]).
                toString();
        return UUID.fromString(sortedUuidString);
    }

    public static UUID getUuidFromUrn(String urn)
    {
        return UUID.fromString(urn.replaceAll("urn:uuid:", ""));
    }
    /**
     *
     * @return the Sting version (in canonical UUID-as-String format) of a UUID generated in the static getUuid()
     * method of this class.
     *
     */
    public static String getUrn()
    {
        return "urn:uuid:" + UuidUtil.getUuid().toString();
    }
}

