package net.smartcosmos.events.impl;

import net.smartcosmos.events.ISmartCosmosEventTemplate;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * @author voor
 */
public abstract class AbstractSmartCosmosEventTemplate
        implements ISmartCosmosEventTemplate {

    public abstract void convertAndSend(SmartCosmosEvent<Object> message)
            throws SmartCosmosEventException;

    @Override
    public void sendEvent(Object data, String eventType)
            throws SmartCosmosEventException {
        sendEvent(data, eventType, null, null);

    }

    @Override
    public void sendEvent(Object data, String eventType, String accountUrn)
            throws SmartCosmosEventException {
        sendEvent(data, eventType, accountUrn, null);
    }

    @Override
    public void sendEvent(Object data, String eventType, SmartCosmosUser user)
            throws SmartCosmosEventException {
        sendEvent(data, eventType, user.getAccountUrn(), user.getUserUrn());
    }

    @Override
    public void sendEvent(Object data, String eventType, String accountUrn,
            String userUrn) throws SmartCosmosEventException {
        sendEvent(SmartCosmosEvent.builder().data(data).accountUrn(accountUrn)
                .userUrn(userUrn).eventType(eventType).build());
    }

    @Override
    public void sendEvent(SmartCosmosEvent event) throws SmartCosmosEventException {
        convertAndSend(event);
    }
}
