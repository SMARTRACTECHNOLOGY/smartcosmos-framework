package net.smartcosmos.model.batch;

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

import net.smartcosmos.model.base.IDomainResource;

public interface IBatchTransmissionReceipt extends IDomainResource<IBatchTransmissionReceipt>
{
    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);

    TransmissionResultType getTransmissionResult();

    void setTransmissionResult(TransmissionResultType transmissionResult);
}
