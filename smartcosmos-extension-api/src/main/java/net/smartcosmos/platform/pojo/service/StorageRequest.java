package net.smartcosmos.platform.pojo.service;

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

import net.smartcosmos.model.context.IUser;
import net.smartcosmos.objects.model.context.IFile;

import java.io.InputStream;

public final class StorageRequest
{
    /*
     * Just puts some commonly used content types here.
     */
    public static final String APPLICATION_JSON = "application/json";

    private InputStream inputStream;

    private IFile file;

    private String fileName;

    private IUser user;

    private long length;

    private String contentType;

    public long getContentLength()
    {
        return length;
    }

    public String getContentType()
    {
        return contentType;
    }

    public StorageRequest(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public StorageRequest setContentType(String contentType)
    {
        this.contentType = contentType;
        return this;
    }

    public StorageRequest setFile(IFile file)
    {
        this.file = file;
        return this;
    }

    public StorageRequest setFileName(String fileName)
    {
        this.fileName = fileName;
        return this;
    }

    public StorageRequest setContentLength(long length)
    {
        this.length = length;
        return this;
    }

    public StorageRequest setUser(IUser user)
    {
        this.user = user;
        return this;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public IFile getFile()
    {
        return file;
    }

    public String getFileName()
    {
        return fileName;
    }

    public IUser getUser()
    {
        return user;
    }
}
