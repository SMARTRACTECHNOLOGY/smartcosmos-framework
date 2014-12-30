package net.smartcosmos.builder;

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

import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.batch.BatchTransmissionReceipt;

public final class BatchTransmissionReceiptBuilder extends AbstractBuilder<IBatchTransmissionReceipt>
{
    public BatchTransmissionReceiptBuilder(String transmissionUrn)
    {
        super(new BatchTransmissionReceipt());
        Preconditions.checkNotNull(transmissionUrn, "transmissionUrn must not be null");
        this.instance.setTransmissionUrn(transmissionUrn);
    }

    public BatchTransmissionReceiptBuilder setTransmissionResult(TransmissionResultType transmissionResultType)
    {
        instance.setTransmissionResult(transmissionResultType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getTransmissionResult(), "transmissionResultType must not be null");
    }
}

