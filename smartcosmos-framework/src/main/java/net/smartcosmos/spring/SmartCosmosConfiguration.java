package net.smartcosmos.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import net.smartcosmos.events.SmartCosmosEventTemplate;
import net.smartcosmos.events.rest.RestSmartCosmosEventTemplate;
import net.smartcosmos.events.test.TestSmartCosmosEventRestTemplate;

/**
 * Essentially a placeholder for now, as more commonly found configurations are discovered
 * they can be added to this configuration for easier management.
 */
@Configuration
@EnableConfigurationProperties({ SmartCosmosProperties.class })
public class SmartCosmosConfiguration {

    /**
     * Provides access to all of the properties defined in the file.
     */
    @Autowired
    private SmartCosmosProperties smartCosmosProperties;

    @Configuration
    @ConditionalOnMissingBean(SmartCosmosEventTemplate.class)
    @ConditionalOnMissingClass("org.springframework.kafka.core.KafkaTemplate")
    protected static class SmartCosmosRestTemplateConfiguration {

        @Autowired
        private SmartCosmosProperties smartCosmosProperties;

        @Bean
        @Profile("!test")
        SmartCosmosEventTemplate smartCosmosEventTemplate(
            OAuth2ClientContext oauth2ClientContext,
            OAuth2ProtectedResourceDetails details, SpringClientFactory clientFactory) {
            RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(
                clientFactory);
            OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details,
                oauth2ClientContext);
            restTemplate.setRequestFactory(ribbonClientHttpRequestFactory);
            return new RestSmartCosmosEventTemplate(restTemplate,
                smartCosmosProperties.getEvents().getServiceName(),
                smartCosmosProperties.getEvents().getHttpMethod(),
                smartCosmosProperties.getEvents().getUrl());
        }

        @Bean
        @Profile("test")
        SmartCosmosEventTemplate SmartCosmosEventRestTemplate() {
            return new TestSmartCosmosEventRestTemplate();
        }
    }



}
