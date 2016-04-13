package net.smartcosmos.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author voor
 */
public interface SmartCosmosEventSink {

	String INPUT = "smartcosmoseventsinput";

	@Input(SmartCosmosEventSink.INPUT)
	SubscribableChannel input();
}
