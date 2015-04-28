package net.smartcosmos.platform.bundle.batch;

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

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.model.batch.IBatchTransmissionResponse;
import net.smartcosmos.platform.api.ProtocolType;
import net.smartcosmos.pojo.batch.BatchTransmissionResponse;

import java.util.UUID;

public final class BatchTransmissionResponseBuilder extends AbstractBuilder<IBatchTransmissionResponse>
{
    public BatchTransmissionResponseBuilder(String transmissionUrn)
    {
        super(new BatchTransmissionResponse());
        Preconditions.checkNotNull(transmissionUrn, "transmissionUrn must not be null");
        this.instance.setTransmissionUrn(transmissionUrn);

        // Included only for consistency - not actually used anywhere by the system
        this.instance.setUrn("urn:uuid:" + UUID.randomUUID().toString());
    }

    public BatchTransmissionResponseBuilder setEndpointUri(String endpointUri)
    {
        instance.setEndpointUri(endpointUri);
        return this;
    }

    public BatchTransmissionResponseBuilder setProtocol(ProtocolType protocolType)
    {
        instance.setProtocol(protocolType.toString());
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getEndpointUri(), "endpointUri must not be null");
        Preconditions.checkNotNull(instance.getProtocol(), "protocol must not be null");
    }
}

