package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Enables Smart Cosmos Event Aspects and annotations that enable automatically configuring and sending events either through the configured event
 * pipeline or through a REST endpoint to a gateway that will thereby send the events to the event pipeline.
 *
 * To be used on {@link org.springframework.context.annotation.Configuration Configuration} classes.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableDiscoveryClient
@Documented
@Import({ SmartCosmosBootstrapConfiguration.class })
public @interface EnableSmartCosmosEvents {
}
