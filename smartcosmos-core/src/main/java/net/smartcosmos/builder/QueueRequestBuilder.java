package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.queue.IQueueRequest;
import net.smartcosmos.pojo.queue.QueueRequest;

public class QueueRequestBuilder extends AbstractReferentialBuilder<IQueueRequest, QueueRequestBuilder>
{
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
