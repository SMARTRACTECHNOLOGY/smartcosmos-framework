package net.smartcosmos.events;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * @author voor
 */
@MessagingGateway
public interface ISmartCosmosEventGateway {

	@Gateway(requestChannel = SmartCosmosEventSource.OUTPUT)
	void convertAndSend(SmartCosmosEvent event);

}
