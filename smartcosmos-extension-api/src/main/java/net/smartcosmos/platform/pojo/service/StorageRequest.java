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

import net.smartcosmos.model.context.IAccount;
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

        private IAccount account;

        private long length;

        private String contentType;

        public StorageRequestBuilder(final InputStream inputStream)
        {
            super();
            Preconditions.checkNotNull(inputStream);
            this.inputStream = inputStream;
        }

        public StorageRequest build()
        {
            IAccount acc;
            if (this.account != null)
            {
                acc = this.account;
            } else if (this.user != null)
            {
                acc = this.user.getAccount();
            } else
            {
                throw new IllegalArgumentException("Must have an account associated with each request!");
            }

            Preconditions.checkNotNull(this.inputStream, "Must have an input stream");

            Preconditions.checkNotNull(this.file, "Must have a FileEntity -- you can create a new one if necessary");

            return new StorageRequest(this.inputStream, this.file, this.fileName, this.user, acc, this.length,
                    this.contentType);
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

        public IAccount getAccount()
        {
            return account;
        }

        public StorageRequestBuilder setAccount(final IAccount account)
        {
            this.account = account;
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

    }

    private final InputStream inputStream;

    private final IFile file;

    private final String filename;

    private final IAccount account;

    private final IUser user;

    private final long length;

    private final String contentType;

    public StorageRequest(final InputStream inputStream, final IFile file, final String filename, final IUser user,
            final IAccount account,
            final long length, final String contentType)
    {
        super();
        /*
         * These are all of the fields that are commonly used in our Storage Service implementations, which means they
         * all need to be present. Better to check this BEFORE you send the request.
         */
        Preconditions.checkNotNull(account);
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
        this.account = account;
    }

    /**
     * @deprecated User is now optional, but Account is not, please use the explicitly declared Account constructor.
     * @param inputStream
     *            input stream to the file that is being stored.
     * @param file
     *            database reference to the file, where the URL to the file will be stored.
     * @param filename
     *            name of the file
     * @param user
     *            user that submitted the request (now optional)
     * @param length
     *            length of file (optional)
     * @param contentType
     *            contentype
     */
    @Deprecated
    public StorageRequest(final InputStream inputStream, final IFile file, final String filename, final IUser user,
            final long length, final String contentType)
    {
        this(inputStream, file, filename, user, user.getAccount(), length, contentType);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StorageRequest other = (StorageRequest) obj;
        if (account == null)
        {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (contentType == null)
        {
            if (other.contentType != null)
                return false;
        } else if (!contentType.equals(other.contentType))
            return false;
        if (file == null)
        {
            if (other.file != null)
                return false;
        } else if (!file.equals(other.file))
            return false;
        if (filename == null)
        {
            if (other.filename != null)
                return false;
        } else if (!filename.equals(other.filename))
            return false;
        if (inputStream == null)
        {
            if (other.inputStream != null)
                return false;
        } else if (!inputStream.equals(other.inputStream))
            return false;
        if (length != other.length)
            return false;
        if (user == null)
        {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    public IAccount getAccount()
    {
        return account;
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

    public String getFilename()
    {
        return filename;
    }

    public String getFileName()
    {
        return this.filename;
    }

    public InputStream getInputStream()
    {
        return this.inputStream;
    }

    public long getLength()
    {
        return length;
    }

    public IUser getUser()
    {
        return this.user;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        result = prime * result + ((filename == null) ? 0 : filename.hashCode());
        result = prime * result + ((inputStream == null) ? 0 : inputStream.hashCode());
        result = prime * result + (int) (length ^ (length >>> 32));
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return "StorageRequest [file=" + file + ", filename=" + filename + ", account=" + account + ", user=" + user +
                ", length=" + length + ", contentType=" + contentType + "]";
    }
}
