package net.smartcosmos.pojo.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
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

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmissionRequest
        extends DomainResource<IBatchTransmissionRequest> implements IBatchTransmissionRequest
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String md5Checksum;

    @JsonView(JsonGenerationView.Minimum.class)
    private String mimeType;

    @JsonView(JsonGenerationView.Minimum.class)
    private String routingUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private int contentLength;

    @Override
    public int getFileContentLength()
    {
        return contentLength;
    }

    @Override
    public void setFileContentLength(int contentLength)
    {
        this.contentLength = contentLength;
    }

    @Override
    public String getFileMd5Checksum()
    {
        return md5Checksum;
    }

    @Override
    public void setFileMd5Checksum(String md5Checksum)
    {
        this.md5Checksum = md5Checksum;
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
    public String getRoutingUrn()
    {
        return routingUrn;
    }

    @Override
    public void setRoutingUrn(String routingUrn)
    {
        this.routingUrn = routingUrn;
    }
}
