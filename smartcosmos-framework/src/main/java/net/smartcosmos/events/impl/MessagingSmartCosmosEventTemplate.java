package net.smartcosmos.events.impl;

import net.smartcosmos.events.ISmartCosmosEventGateway;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author voor
 */
@Component
public class MessagingSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

	@Autowired
	ISmartCosmosEventGateway smartCosmosEventGateway;

	@Override
	public void convertAndSend(SmartCosmosEvent message)
			throws SmartCosmosEventException {
		smartCosmosEventGateway.convertAndSend(message);
	}
}
