package net.smartcosmos.events.impl;

import net.smartcosmos.events.SmartCosmosEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import net.smartcosmos.events.ISmartCosmosEventGateway;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;

/**
 * @author voor
 */
@Component
public class MessagingSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

    private final ISmartCosmosEventGateway smartCosmosEventGateway;

    @Value("${smart-cosmos.event-header}")
    private String smartCosmosEventSource;

    @Autowired
    public MessagingSmartCosmosEventTemplate(
            ISmartCosmosEventGateway smartCosmosEventGateway) {
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
