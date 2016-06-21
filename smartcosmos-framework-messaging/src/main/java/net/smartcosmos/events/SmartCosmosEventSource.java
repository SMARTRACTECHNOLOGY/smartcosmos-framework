package net.smartcosmos.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author voor
 */
public interface SmartCosmosEventSource {

    String OUTPUT = "smartcosmos-events-output";

    @Output(SmartCosmosEventSource.OUTPUT)
    MessageChannel output();
}
