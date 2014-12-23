package net.smartcosmos.model.queue;

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
