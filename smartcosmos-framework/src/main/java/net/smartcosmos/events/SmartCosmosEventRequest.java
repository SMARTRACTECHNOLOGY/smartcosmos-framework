package net.smartcosmos.events;

import java.net.URI;

import lombok.Builder;
import lombok.Data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

/**
 * @author voor
 */
@Data
@Builder
public class SmartCosmosEventRequest<T> {
	private String serviceName;
	private String url;
	private SmartCosmosEvent<T> event;
	private HttpMethod httpMethod;

	public RequestEntity<SmartCosmosEvent<T>> buildRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		URI uri = URI.create("http://" + serviceName + "/" + url);
		return new RequestEntity<>(event, headers, httpMethod, uri);
	}
}
