package net.smartcosmos.events.rest;

import java.net.URI;
import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import net.smartcosmos.events.AbstractSmartCosmosEventTemplate;
import net.smartcosmos.events.SmartCosmosEvent;
import net.smartcosmos.events.SmartCosmosEventException;

/**
 * REST implementation of the Event Template, created as a fallback if the individual service does not have messaging dependencies.
 *
 * @see net.smartcosmos.events.SmartCosmosEventTemplate
 **/
@Slf4j
public class RestSmartCosmosEventTemplate extends AbstractSmartCosmosEventTemplate {

    private final RestOperations restOperations;
    private final HttpMethod eventHttpMethod;
    private final URI eventUri;
    private final OAuth2ClientContext oAuth2ClientContext;

    private final Executor smartCosmosEventTaskExecutor;

    public RestSmartCosmosEventTemplate(
        RestOperations restOperations,
        OAuth2ClientContext oAuth2ClientContext, String eventServiceName,
        HttpMethod eventHttpMethod,
        String eventUrl,
        Executor smartCosmosEventTaskExecutor) {
        this.restOperations = restOperations;
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.eventHttpMethod = eventHttpMethod;
        this.eventUri = URI.create("http://" + eventServiceName + "/" + eventUrl);
        this.smartCosmosEventTaskExecutor = smartCosmosEventTaskExecutor;
    }

    @Override
    public void convertAndSend(SmartCosmosEvent<Object> message)
        throws SmartCosmosEventException {

        HttpHeaders eventHttpHeaders = new HttpHeaders();
        eventHttpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        // oAuth2ClientContext is not thread safe, so we need to retrieve this before.
        OAuth2AccessToken accessToken = oAuth2ClientContext.getAccessToken();
        String tokenType = accessToken.getTokenType();
        if (!StringUtils.hasText(tokenType)) {
            tokenType = OAuth2AccessToken.BEARER_TYPE; // we'll assume basic bearer token type if none is specified.
        }
        eventHttpHeaders.set("Authorization", String.format("%s %s", tokenType, accessToken.getValue()));

        try {
            smartCosmosEventTaskExecutor.execute(() -> restOperations.exchange(eventUri,
                                                                               eventHttpMethod,
                                                                               new HttpEntity<>(message, eventHttpHeaders),
                                                                               Void.class));
        } catch (Exception e) {
            log.trace(e.getMessage(), e);
            throw new SmartCosmosEventException(
                "Failed to send event of type " + message.getEventType(), e);
        }
    }

}
