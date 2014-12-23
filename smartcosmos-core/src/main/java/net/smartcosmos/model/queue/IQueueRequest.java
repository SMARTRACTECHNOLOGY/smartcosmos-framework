package net.smartcosmos.model.queue;

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

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IMinimalReferentialObject;
import net.smartcosmos.model.base.IMoniker;

import java.util.Map;

public interface IQueueRequest extends IMinimalReferentialObject, IMoniker
{
    String DEFAULT_QUEUE_NAME = "**DEFAULT**";

    String getQueueName();

    void setQueueName(String queueName);

    String getMessageBody();

    void setMessageBody(String messageBody);

    Map<String, String> getMessageAttributes();

    void addMessageAttribute(String key, String value);

    void setReferenceUrn(String urn);

    void setEntityReferenceType(EntityReferenceType entityReferenceType);
}
