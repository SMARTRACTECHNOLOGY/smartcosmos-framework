package net.smartcosmos.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author voor
 */
public interface SmartCosmosEventSource {

	String OUTPUT = "smartcosmoseventsoutput";

	@Output(SmartCosmosEventSource.OUTPUT)
	MessageChannel output();
}
