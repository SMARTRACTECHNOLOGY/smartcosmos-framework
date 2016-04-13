package net.smartcosmos.events;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * This is a placeholder interface that facilitates autoconfiguration, allowing an event
 * service to override the default template if need be.
 *
 * @author voor
 */
public interface ISmartCosmosEventTemplate {

    void sendEvent(Object data, String eventType) throws SmartCosmosEventException;

    void sendEvent(Object data, String eventType, String accountUrn)
            throws SmartCosmosEventException;

    void sendEvent(Object data, String eventType, SmartCosmosUser user)
            throws SmartCosmosEventException;

    void sendEvent(Object data, String eventType, String accountUrn, String userUrn)
            throws SmartCosmosEventException;
}
