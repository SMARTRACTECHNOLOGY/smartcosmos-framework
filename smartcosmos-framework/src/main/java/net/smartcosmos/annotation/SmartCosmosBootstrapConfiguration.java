package net.smartcosmos.annotation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.smartcosmos.events.SendsSmartCosmosEventAdvice;
import net.smartcosmos.events.SmartCosmosEventTemplate;
import net.smartcosmos.events.rest.RestSmartCosmosEventTemplate;
import net.smartcosmos.events.test.TestSmartCosmosEventRestTemplate;
import net.smartcosmos.spring.SmartCosmosProperties;

/**
 * {@code @Configuration} class that registers a
 */
@Configuration
@EnableConfigurationProperties({ SmartCosmosProperties.class })
public class SmartCosmosBootstrapConfiguration {

    /**
     * Provides access to all of the properties defined in the file.
     */
    @Autowired
    private SmartCosmosProperties smartCosmosProperties;

    @Configuration
    @ConditionalOnBean(FormatterRegistrar.class)
    static class FormatterRegistrarConfiguration extends WebMvcConfigurerAdapter {
        @Autowired(required = false)
        Map<String, FormatterRegistrar> formatterRegistrarMap;

        @Override
        public void addFormatters(FormatterRegistry registry) {
            if (formatterRegistrarMap != null) {
                for (FormatterRegistrar registrar : formatterRegistrarMap.values()) {
                    registrar.registerFormatters(registry);
                }
            }
        }
    }

    @Bean
    @SuppressWarnings("rawtypes")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public SendsSmartCosmosEventAnnotationBeanPostProcessor sendsSmartCosmosEventAnnotationBeanPostProcessor() {
        return new SendsSmartCosmosEventAnnotationBeanPostProcessor();
    }

    @Bean
    public SendsSmartCosmosEventAdvice sendsSmartCosmosEventAdvice(SmartCosmosEventTemplate smartCosmosEventTemplate) {
return new SendsSmartCosmosEventAdvice(smartCosmosEventTemplate);
    }

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
