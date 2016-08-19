package net.smartcosmos.annotation;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.smartcosmos.concurrent.DelegatingSecurityContextAndRequestAttributesExecutorService;
import net.smartcosmos.events.SendsSmartCosmosEventAdvice;
import net.smartcosmos.events.SmartCosmosEventTemplate;
import net.smartcosmos.events.rest.RestSmartCosmosEventTemplate;
import net.smartcosmos.events.test.TestSmartCosmosEventRestTemplate;

/**
 * {@code @Configuration} class that registers a
 */
@Configuration
public class SmartCosmosBootstrapConfiguration {

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
    @EnableConfigurationProperties({ SmartCosmosEventsProperties.class })
    @ConditionalOnMissingClass("org.springframework.kafka.core.KafkaTemplate")
    protected static class SmartCosmosRestTemplateConfiguration {

        /**
         * Provides access to all of the properties defined in the file.
         */
        @Autowired
        private SmartCosmosEventsProperties smartCosmosEventsProperties;

        @Bean
        @Autowired
        @Profile("!test")
        SmartCosmosEventTemplate smartCosmosEventTemplate(
            RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory,
            Executor smartCosmosEventTaskExecutor) {

            RestTemplate eventRestTemplate = new RestTemplate();
            eventRestTemplate.setRequestFactory(ribbonClientHttpRequestFactory);
            return new RestSmartCosmosEventTemplate(eventRestTemplate,
                                                    smartCosmosEventsProperties.getServiceName(),
                                                    smartCosmosEventsProperties.getHttpMethod(),
                                                    smartCosmosEventsProperties.getUrl(),
                                                    smartCosmosEventTaskExecutor);
        }

        @Bean
        @Profile("test")
        SmartCosmosEventTemplate SmartCosmosEventRestTemplate() {

            return new TestSmartCosmosEventRestTemplate();
        }
    }

    @Configuration
    @EnableAsync
    protected static class AsyncConfig {

        @Bean
        public Executor smartCosmosEventTaskExecutor() {

            ExecutorService executorService = Executors.newCachedThreadPool();
            return new DelegatingSecurityContextAndRequestAttributesExecutorService(executorService);
        }

    }
}
