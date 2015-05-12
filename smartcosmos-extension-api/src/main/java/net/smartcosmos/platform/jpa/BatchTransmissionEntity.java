package net.smartcosmos.platform.jpa;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2014 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.model.batch.BatchProcessorStatus;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.platform.api.ProtocolType;
import net.smartcosmos.platform.api.batch.IBatchTransmission;
import net.smartcosmos.platform.bundle.batch.BatchTransmissionResponseBuilder;
import net.smartcosmos.platform.jpa.base.DomainResourceEntity;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "batch_transmission")
public class BatchTransmissionEntity extends DomainResourceEntity<IBatchTransmission> implements IBatchTransmission
{
    @JsonView(JsonGenerationView.Minimum.class)
    @Basic
    protected int contentLength;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 100, nullable = false, updatable = false)
    protected String mimeType;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 128, nullable = false, updatable = false)
    protected String md5Checksum;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = false, updatable = false)
    protected String routingUrn;

    @JsonView(JsonGenerationView.Standard.class)
    @Enumerated(EnumType.STRING)
    protected TransmissionResultType transmissionResult;

    @JsonView(JsonGenerationView.Standard.class)
    @Enumerated(EnumType.STRING)
    protected ProtocolType uploadProtocol;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = false, updatable = false)
    protected String transmissionUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = false, updatable = false)
    protected String endpointUri;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = false, updatable = false)
    protected String bucketName;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = false, updatable = false)
    protected String objectKey;

    @JsonView(JsonGenerationView.Full.class)
    @Basic
    protected long batchProcessorStartTimestamp;

    @JsonView(JsonGenerationView.Standard.class)
    @Enumerated(EnumType.STRING)
    protected BatchProcessorStatus batchProcessorStatus;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    protected long lastPercentageCompleteUpdateTimestamp;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    protected int percentageComplete = 0;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    protected int errorCode = 0;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 1024, nullable = true, updatable = false)
    protected String errorMessage;

    @JsonView(JsonGenerationView.Restricted.class)
    @Basic
    protected long batchProcessorStopTimestamp;

    @Override
    public void copy(IBatchTransmission target)
    {
        super.copy(target);

        this.contentLength = target.getFileContentLength();
        this.mimeType = target.getMimeType();
        this.md5Checksum = target.getFileMd5Checksum();
        this.routingUrn = target.getRoutingUrn();

        this.transmissionUrn = target.getTransmissionUrn();
        this.endpointUri = target.getEndpointUri();
        this.bucketName = target.getBucketName();
        this.objectKey = target.getObjectKey();
        this.uploadProtocol = target.getUploadProtocol();

        this.transmissionResult = target.getTransmissionResult();

        this.batchProcessorStartTimestamp = target.getBatchProcessorStartTimestamp();
        this.batchProcessorStopTimestamp = target.getBatchProcessorStopTimestamp();
        this.errorCode = target.getErrorCode();
        this.errorMessage = target.getErrorMessage();
        this.percentageComplete = target.getPercentageComplete();
        this.lastPercentageCompleteUpdateTimestamp = target.getLastPercentageCompleteUpdateTimestamp();
        this.batchProcessorStatus = target.getBatchProcessorStatus();
    }

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

    @Override
    public String getEndpointUri()
    {
        return endpointUri;
    }

    @Override
    public void setEndpointUri(String endpointUri)
    {
        this.endpointUri = endpointUri;
    }

    @Override
    public String getTransmissionUrn()
    {
        return transmissionUrn;
    }

    @Override
    public void setTransmissionUrn(String transmissionUrn)
    {
        this.transmissionUrn = transmissionUrn;
    }

    @Override
    public TransmissionResultType getTransmissionResult()
    {
        return transmissionResult;
    }

    @Override
    public void setTransmissionResult(TransmissionResultType transmissionResult)
    {
        this.transmissionResult = transmissionResult;
    }

    @Override
    public long getBatchProcessorStartTimestamp()
    {
        return batchProcessorStartTimestamp;
    }

    @Override
    public void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp)
    {
        this.batchProcessorStartTimestamp = batchProcessorStartTimestamp;
    }

    @Override
    public int getPercentageComplete()
    {
        return percentageComplete;
    }

    @Override
    public void setPercentageComplete(int percentageComplete)
    {
        this.percentageComplete = percentageComplete;
    }

    @Override
    public long getLastPercentageCompleteUpdateTimestamp()
    {
        return lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp)
    {
        this.lastPercentageCompleteUpdateTimestamp = lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public long getBatchProcessorStopTimestamp()
    {
        return batchProcessorStopTimestamp;
    }

    @Override
    public void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp)
    {
        this.batchProcessorStopTimestamp = batchProcessorStopTimestamp;
    }

    @Override
    public BatchProcessorStatus getBatchProcessorStatus()
    {
        return batchProcessorStatus;
    }

    @Override
    public void setBatchProcessorStatus(BatchProcessorStatus batchProcessorStatus)
    {
        this.batchProcessorStatus = batchProcessorStatus;
    }

    @Override
    public IBatchTransmissionResponse toTransmissionResponse()
    {
        return new BatchTransmissionResponseBuilder(this.transmissionUrn)
                .setEndpointUri(this.endpointUri)
                .setProtocol(this.uploadProtocol)
                .build();
    }

    @Override
    public String getBucketName()
    {
        return bucketName;
    }

    @Override
    public void setBucketName(String bucketName)
    {
        this.bucketName = bucketName;
    }

    @Override
    public String getObjectKey()
    {
        return objectKey;
    }

    @Override
    public void setObjectKey(String objectKey)
    {
        this.objectKey = objectKey;
    }

    @Override
    public ProtocolType getUploadProtocol()
    {
        return uploadProtocol;
    }

    @Override
    public void setUploadProtocol(ProtocolType protocol)
    {
        this.uploadProtocol = protocol;
    }
}
