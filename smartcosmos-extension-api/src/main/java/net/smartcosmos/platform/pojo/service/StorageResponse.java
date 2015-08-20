package net.smartcosmos.platform.pojo.service;

import net.smartcosmos.objects.model.context.IFile;

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

public final class StorageResponse
{
    private final String url;

    private final String contentHash;

    private final IFile file;

    public StorageResponse(final IFile file, final String url, final String contentHash)
    {
        super();
        this.file = file;
        this.url = url;
        this.contentHash = contentHash;
    }

    /**
     * @return the contentHash
     */
    public String getContentHash()
    {
        return this.contentHash;
    }

    public String getDigitalSignature()
    {
        return this.contentHash;
    }

    /**
     * @return the file
     */
    public IFile getFile()
    {
        return this.file;
    }

    public String getUrl()
    {
        return this.url;
    }

}
