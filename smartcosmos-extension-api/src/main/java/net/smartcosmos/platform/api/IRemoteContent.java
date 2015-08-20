package net.smartcosmos.platform.api;

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

import java.io.IOException;
import java.io.InputStream;

/**
 * Simple wrapper around an input stream at the other end of an HTTP/S connection.
 */
public interface IRemoteContent extends AutoCloseable
{
    /**
     * Identifies if the remote content is available (200).
     *
     * @return valid HTTP status code
     */
    int getStatusCode();

    /**
     * If {@link #getStatusCode()} is 200, defines the content length available as described in the HTTP headers.
     *
     * @return content length defined in the HTTP headers
     */
    long getContentLength();

    /**
     * Access to the actual input stream that contains the remote content.
     *
     * @return Input Stream
     * @throws IOException when the remote content cannot be accessed
     */
    InputStream getContent() throws IOException;
}
