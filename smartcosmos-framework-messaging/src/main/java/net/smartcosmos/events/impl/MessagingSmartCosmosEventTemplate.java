package net.smartcosmos.events.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import net.smartcosmos.events.AbstractSmartCosmosEventTemplate;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;
import net.smartcosmos.events.SmartCosmosEventGateway;

/**
 * @author voor
 */
@Component
public class MessagingSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

    private final SmartCosmosEventGateway smartCosmosEventGateway;

    @Value("${smartcosmos.event-header}")
    private String smartCosmosEventSource;

    @Autowired
    public MessagingSmartCosmosEventTemplate(
            SmartCosmosEventGateway smartCosmosEventGateway) {
        this.smartCosmosEventGateway = smartCosmosEventGateway;
    }

    @Override
    public void convertAndSend(SmartCosmosEvent message)
            throws SmartCosmosEventException {
        smartCosmosEventGateway
                .convertAndSend(
                        MessageBuilder.withPayload(message.getData())
                                .setHeader("requestChannel",
                                        smartCosmosEventSource + message.getEventType())
                                .setHeader("X-accountUrn",message.getAccountUrn())
                                .setHeader("X-userUrn",message.getUserUrn())
                                .setHeader("X-eventTypeUrn",message.getEventType())
                                .build());
    }
}
