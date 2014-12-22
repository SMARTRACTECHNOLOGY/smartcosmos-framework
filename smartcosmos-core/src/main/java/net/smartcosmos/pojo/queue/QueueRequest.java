package net.smartcosmos.pojo.queue;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Maps;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
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
    public void setEntityReferenceType(EntityReferenceType entityReferenceType)
    {
        this.entityReferenceType = entityReferenceType;
    }

    @Override
    public String getReferenceUrn()
    {
        return referenceUrn;
    }

    @Override
    public void setReferenceUrn(String urn)
    {
        this.referenceUrn = urn;
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
    public IAccount getAccount()
    {
        throw new UnsupportedOperationException("Account is not supported on a queue request");
    }

    @Override
    public void setAccount(IAccount account)
    {
        throw new UnsupportedOperationException("Account is not supported on a queue request");
    }
}
