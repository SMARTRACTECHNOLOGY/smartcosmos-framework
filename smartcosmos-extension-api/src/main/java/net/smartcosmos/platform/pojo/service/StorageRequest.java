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

import java.io.InputStream;

import com.google.common.base.Preconditions;

import net.smartcosmos.model.context.IUser;
import net.smartcosmos.objects.model.context.IFile;

public final class StorageRequest
{
    public static class StorageRequestBuilder
    {

        private final InputStream inputStream;

        private IFile file;

        private String fileName;

        private IUser user;

        private long length;

        private String contentType;

        public StorageRequestBuilder(final InputStream inputStream)
        {
            super();
            Preconditions.checkNotNull(inputStream);
            this.inputStream = inputStream;
        }

        /**
         * @return the contentType
         */
        public String getContentType()
        {
            return this.contentType;
        }

        /**
         * @return the file
         */
        public IFile getFile()
        {
            return this.file;
        }

        /**
         * This is the filename that the file should be saved as. This is an *internal* name, and can be completely
         * hidden from the user. This might be necessary for de-duping or other abstracting.
         * 
         * @return the fileName
         */
        public String getFileName()
        {
            return this.fileName;
        }

        /**
         * @return the inputStream
         */
        public InputStream getInputStream()
        {
            return this.inputStream;
        }

        /**
         * @return the length
         */
        public long getLength()
        {
            return this.length;
        }

        /**
         * @return the user
         */
        public IUser getUser()
        {
            return this.user;
        }

        public StorageRequestBuilder setContentLength(final long length)
        {
            this.length = length;
            return this;
        }

        public StorageRequestBuilder setContentType(final String contentType)
        {
            this.contentType = contentType;
            return this;
        }

        public StorageRequestBuilder setFile(final IFile file)
        {
            this.file = file;
            return this;
        }

        public StorageRequestBuilder setFileName(final String fileName)
        {
            this.fileName = fileName;
            return this;
        }

        /**
         * @param length
         *            the length to set
         */
        public void setLength(final long length)
        {
            this.length = length;
        }

        public StorageRequestBuilder setUser(final IUser user)
        {
            this.user = user;
            return this;
        }

        public StorageRequest build()
        {
            return new StorageRequest(this.inputStream, this.file, this.fileName, this.user, this.length,
                    this.contentType);
        }

    }

    private final InputStream inputStream;

    private final IFile file;

    private final String filename;

    private final IUser user;

    private final long length;

    private final String contentType;

    public StorageRequest(final InputStream inputStream, final IFile file, final String filename, final IUser user,
            final long length, final String contentType)
    {
        super();
        /*
         * These are all of the fields that are commonly used in our Storage Service implementations, which means they
         * all need to be present. Better to check this BEFORE you send the request.
         */
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(user.getAccount().getUrn());
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(filename);
        Preconditions.checkNotNull(file.getEntityReferenceType().name());
        Preconditions.checkNotNull(file.getReferenceUrn());
        Preconditions.checkNotNull(file.getTimestamp());
        this.inputStream = inputStream;
        this.file = file;
        this.filename = filename;
        this.user = user;
        this.length = length;
        this.contentType = contentType;
    }

    public long getContentLength()
    {
        return this.length;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public IFile getFile()
    {
        return this.file;
    }

    public String getFileName()
    {
        return this.filename;
    }

    public InputStream getInputStream()
    {
        return this.inputStream;
    }

    public IUser getUser()
    {
        return this.user;
    }
}
