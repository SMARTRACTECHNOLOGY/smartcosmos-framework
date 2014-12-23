package net.smartcosmos.pojo.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmissionReceipt extends DomainResource<IBatchTransmissionReceipt> implements IBatchTransmissionReceipt
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String transmissionUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private TransmissionResultType transmissionResult;

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

    public TransmissionResultType getTransmissionResult()
    {
        return transmissionResult;
    }

    public void setTransmissionResult(TransmissionResultType transmissionResult)
    {
        this.transmissionResult = transmissionResult;
    }
}
