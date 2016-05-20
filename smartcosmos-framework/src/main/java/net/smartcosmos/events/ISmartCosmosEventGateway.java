package net.smartcosmos.events;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * @author voor
 */
@MessagingGateway
public interface ISmartCosmosEventGateway {

    @Gateway
    void convertAndSend(Message message);

}
