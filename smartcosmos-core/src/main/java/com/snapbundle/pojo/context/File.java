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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IFile;
import com.snapbundle.model.context.IUser;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class File extends ReferentialObject<IFile> implements IFile
{
    @JsonView(JsonGenerationView.Standard.class)
    @JsonDeserialize(as = User.class)
    protected IUser user;

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

    public static IFile fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, File.class);
    }

    @Override
    public void copy(IFile file)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

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
    public IUser getUser()
    {
        return user;
    }

    @Override
    public void setUser(IUser user)
    {
        this.user = user;
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
}
