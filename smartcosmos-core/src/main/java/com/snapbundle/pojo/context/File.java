/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.model.context.IFile;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.json.JsonGenerationView;

public class File extends ReferentialObject<IFile> implements IFile
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String url;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String mimeType;

    @JsonView(JsonGenerationView.Standard.class)
    protected long timestamp;

    @JsonView(JsonGenerationView.Full.class)
    protected boolean pending;

    @JsonView(JsonGenerationView.Full.class)
    protected String contentHash;

    @JsonView(JsonGenerationView.Standard.class)
    protected String fileName;

    @Override
    public long getTimestamp()
    {
        return timestamp;
    }

    @Override
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public boolean isPending()
    {
        return pending;
    }

    @Override
    public void setPending(boolean flag)
    {
        this.pending = flag;
    }

    @Override
    public String getFileName()
    {
        return fileName;
    }

    @Override
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String getMimeType()
    {
        return mimeType;
    }

    @Override
    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    @Override
    public void setContentHash(String contentHash)
    {
        this.contentHash = contentHash;
    }

    @Override
    public String getContentHash()
    {
        return contentHash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        File file = (File) o;

        if (pending != file.pending) return false;
        if (timestamp != file.timestamp) return false;
        if (contentHash != null ? !contentHash.equals(file.contentHash) : file.contentHash != null) return false;
        if (fileName != null ? !fileName.equals(file.fileName) : file.fileName != null) return false;
        if (!mimeType.equals(file.mimeType)) return false;
        if (url != null ? !url.equals(file.url) : file.url != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + mimeType.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (pending ? 1 : 0);
        result = 31 * result + (contentHash != null ? contentHash.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
