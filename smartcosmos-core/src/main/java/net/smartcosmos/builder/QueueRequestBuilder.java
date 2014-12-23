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
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.queue.IQueueRequest;
import net.smartcosmos.pojo.queue.QueueRequest;

public class QueueRequestBuilder extends AbstractReferentialBuilder<IQueueRequest, QueueRequestBuilder>
{
    public QueueRequestBuilder()
    {
        super(new QueueRequest());
        instance.setQueueName(IQueueRequest.DEFAULT_QUEUE_NAME);
    }

    public QueueRequestBuilder(String queueName)
    {
        super(new QueueRequest());
        Preconditions.checkNotNull(queueName, "queueName must not be null");
        instance.setQueueName(queueName);
    }

    public QueueRequestBuilder setMessageBody(String messageBody)
    {
        instance.setMessageBody(messageBody);
        return this;
    }

    public QueueRequestBuilder setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        instance.setEntityReferenceType(entityReferenceType);
        return this;
    }

    public QueueRequestBuilder setReferenceUrn(String referenceUrn)
    {
        instance.setReferenceUrn(referenceUrn);
        return this;
    }

    public QueueRequestBuilder setMoniker(String moniker)
    {
        instance.setMoniker(moniker);
        return this;
    }

    public QueueRequestBuilder addMessageAttribute(String key, String value)
    {
        instance.getMessageAttributes().put(key, value);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getEntityReferenceType(), "entityReferenceType must not be null");
        Preconditions.checkNotNull(instance.getReferenceUrn(), "referenceUrn must not be null");
        Preconditions.checkNotNull(instance.getMessageBody(), "messageBody must not be null");
    }
}
