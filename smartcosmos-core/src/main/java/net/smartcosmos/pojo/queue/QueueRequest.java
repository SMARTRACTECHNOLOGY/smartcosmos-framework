package net.smartcosmos.pojo.queue;

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
import com.google.common.collect.Maps;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.queue.IQueueRequest;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.Map;

public class QueueRequest implements IQueueRequest
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected EntityReferenceType entityReferenceType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String referenceUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String queueName;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String messageBody;

    @JsonView(JsonGenerationView.Minimum.class)
    protected Map<String, String> messageAttributes = Maps.newHashMap();

    @JsonView(JsonGenerationView.Minimum.class)
    protected String moniker;

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }

    @Override
    public String getReferenceUrn()
    {
        return referenceUrn;
    }

    @Override
    public String getQueueName()
    {
        return queueName;
    }

    @Override
    public void setQueueName(String queueName)
    {
        this.queueName = queueName;
    }

    @Override
    public String getMessageBody()
    {
        return messageBody;
    }

    @Override
    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }

    @Override
    public Map<String, String> getMessageAttributes()
    {
        return messageAttributes;
    }

    @Override
    public void addMessageAttribute(String key, String value)
    {
        this.messageAttributes.put(key, value);
    }

    @Override
    public String getMoniker()
    {
        return moniker;
    }

    @Override
    public void setMoniker(String moniker)
    {
        this.moniker = moniker;
    }

    @Override
    public void setReferenceUrn(String urn)
    {
        this.referenceUrn = urn;
    }

    @Override
    public void setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        this.entityReferenceType = entityReferenceType;
    }
}
