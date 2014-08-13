/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.client.objects.file;

import net.smartcosmos.client.connectivity.ServerContext;

public final class FileFactory
{
    private FileFactory()
    {
    }

    /**
     * Creates a new instance of a file client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IFileClient createClient(ServerContext context)
    {
        return new FileClient(context);
    }
}
