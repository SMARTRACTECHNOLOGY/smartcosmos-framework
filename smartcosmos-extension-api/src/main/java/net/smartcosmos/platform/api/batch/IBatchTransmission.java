package net.smartcosmos.platform.api.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Batch Processing Framework
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

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.batch.BatchProcessorStatus;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.platform.api.ProtocolType;

public interface IBatchTransmission extends IDomainResource<IBatchTransmission>, IBatchPackageDefinition
{
    ProtocolType getUploadProtocol();

    void setUploadProtocol(ProtocolType protocol);

    /**
     * Identifies the endpoint <i>where</i> the caller should transmit the actual file content.
     *
     * @return Endpoint where the actual file should be sent
     */
    String getEndpointUri();

    void setEndpointUri(String endpointUri);

    long getBatchProcessorStartTimestamp();

    void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp);

    BatchProcessorStatus getBatchProcessorStatus();

    void setBatchProcessorStatus(BatchProcessorStatus batchProcessorStatus);

    int getPercentageComplete();

    void setPercentageComplete(int percentageComplete);

    long getLastPercentageCompleteUpdateTimestamp();

    void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp);

    int getErrorCode();

    void setErrorCode(int errorCode);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);

    long getBatchProcessorStopTimestamp();

    void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp);

    TransmissionResultType getTransmissionResult();

    void setTransmissionResult(TransmissionResultType transmissionResult);

    IBatchTransmissionResponse toTransmissionResponse();
}
