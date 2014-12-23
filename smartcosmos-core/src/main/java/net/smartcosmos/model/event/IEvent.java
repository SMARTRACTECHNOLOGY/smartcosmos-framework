package net.smartcosmos.model.event;

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;

/**
 * Flexible mechanism for capturing user interactions with the platform. The source
 * field contains JSON values that correlate with the specific {@link EventType}. For
 * example, if the event is {@link EventType#ObjectAccessed}, then the {@link #getSource()} will
 * contain the {@link net.smartcosmos.util.json.JsonGenerationView.Standard} serialization of
 * the {@link net.smartcosmos.objects.model.context.IObject} that was accessed.
 */
public interface IEvent extends IDomainResource<IEvent>
{
    EventType getEventType();

    void setEventType(EventType eventType);

    IAccount getAccount();

    void setAccount(IAccount account);

    IUser getUser();

    void setUser(IUser user);

    String getSource();

    void setSource(String source);
}
