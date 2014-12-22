package net.smartcosmos.model.queue;

import net.smartcosmos.model.base.IMoniker;
import net.smartcosmos.model.base.IReferentialObject;

import java.util.Map;

public interface IQueueRequest extends IReferentialObject, IMoniker
{
    String getQueueName();

    void setQueueName(String queueName);

    String getMessageBody();

    void setMessageBody(String messageBody);

    Map<String, String> getMessageAttributes();

    void addMessageAttribute(String key, String value);
}
