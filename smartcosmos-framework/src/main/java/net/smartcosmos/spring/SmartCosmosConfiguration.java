package net.smartcosmos.spring;

import lombok.extern.slf4j.Slf4j;
import net.smartcosmos.events.ISmartCosmosEventTemplate;
import net.smartcosmos.events.impl.RestSmartCosmosEventTemplate;
import net.smartcosmos.events.impl.TestSmartCosmosEventRestTemplate;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.web.client.RestTemplate;

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
	@ConditionalOnClass({ ConnectionFactory.class, RabbitOperations.class,
			RabbitTemplate.class })
	@Slf4j
	protected static class RabbitConnectionConfiguration
			implements RabbitListenerConfigurer {

		@Autowired
		private SmartCosmosProperties smartCosmosProperties;

		@Autowired
		private ConnectionFactory connectionFactory;

		@Bean
		public MappingJackson2MessageConverter jackson2Converter() {
			MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
			return converter;
		}

		@Bean
		public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
			DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
			factory.setMessageConverter(jackson2Converter());
			return factory;
		}

		@Override
		public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
			registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
		}
	}

	@Configuration
	@ConditionalOnMissingBean(ISmartCosmosEventTemplate.class)
	@ConditionalOnMissingClass("org.springframework.amqp.rabbit.core.RabbitOperations")
	protected static class SmartCosmosEventConfiguration {

		@Autowired
		private SmartCosmosProperties smartCosmosProperties;

		@Bean
		@Profile("!test")
		ISmartCosmosEventTemplate smartCosmosEventTemplate(
				SpringClientFactory clientFactory, LoadBalancerClient loadBalancer) {
			RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(
					clientFactory, loadBalancer);
			return new RestSmartCosmosEventTemplate(
					new RestTemplate(ribbonClientHttpRequestFactory),
					smartCosmosProperties.getEvents().getServiceName(),
					smartCosmosProperties.getEvents().getHttpMethod(),
					smartCosmosProperties.getEvents().getUrl());
		}

		@Bean
		@Profile("test")
		ISmartCosmosEventTemplate SmartCosmosEventRestTemplate() {
			return new TestSmartCosmosEventRestTemplate();
		}
	}

}
