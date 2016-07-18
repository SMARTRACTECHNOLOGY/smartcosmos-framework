package net.smartcosmos.spring;

import java.lang.annotation.*;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import net.smartcosmos.annotation.SmartCosmosBootstrapConfiguration;

/**
 * Enables a service to send events to the event pipeline, this annotation was deprecated in favor of more descriptive packaging.
 *
 * @see net.smartcosmos.annotation.EnableSmartCosmosEvents
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableDiscoveryClient
@Documented
@Import({ SmartCosmosBootstrapConfiguration.class })
@Deprecated
public @interface EnableSmartCosmos {
}
