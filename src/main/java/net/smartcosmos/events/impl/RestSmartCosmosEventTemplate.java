package net.smartcosmos.events.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;
import net.smartcosmos.events.SmartCosmosEventRequest;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestOperations;

/**
 * @author voor
 */
@AllArgsConstructor
@Slf4j
public class RestSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

	private final RestOperations restOperations;
	private final String eventServiceName;
	private final HttpMethod eventHttpMethod;
	private final String eventUrl;

	@Override
	public void convertAndSend(SmartCosmosEvent message)
			throws SmartCosmosEventException {
		try {
			RequestEntity request = SmartCosmosEventRequest.builder().event(message)
					.httpMethod(eventHttpMethod).serviceName(eventServiceName)
					.url(eventUrl).build().buildRequest();

			restOperations.exchange(request, Void.class);
		}
		catch (Exception e) {
			log.trace(e.getMessage(), e);
			throw new SmartCosmosEventException(
					"Failed to send event of type " + message.getEventType(), e);
		}
	}

}
