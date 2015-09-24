package net.smartcosmos.platform.api.service;

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

import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.pojo.service.StorageRequest;
import net.smartcosmos.platform.pojo.service.StorageResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides an abstraction layer for a remote storage service, e.g. AWS S3, Box.com, DropBox, or any other type of
 * storage system.
 */
public interface IStorageService extends IService, IHealthCheckable
{
    /**
     * Store the content represented by the storage request at the remote location.
     *
     * @param request Storage request defining the content to persist
     * @return Storage outcome
     * @throws IOException whenever there is an exception persisting the content to the remote location
     */
    StorageResponse store(StorageRequest request) throws IOException;

    /**
     * Access a previously persisted file.
     *
     * @param file File to access
     * @return Input stream that contains the raw contents of the file pointed to
     * by {@link net.smartcosmos.objects.model.context.IFile}
     * @throws IOException whenever there is an exception access the remote content
     */
    InputStream retrieve(IFile file) throws IOException;

    /**
     * Permanently delete the file contents pointed to by the {@link net.smartcosmos.objects.model.context.IFile}.
     * <p>
     * <b>NOTE:</b> Each concrete implementation must define its own purge guarantees. For example, if the file's space
     * should be securely wiped by overwriting with 0s, it is the responsibility of the concrete implementation to
     * ensure this type of service level agreement.
     *
     * @param file
     * @throws IOException
     */
    void delete(IFile file) throws IOException;
}
