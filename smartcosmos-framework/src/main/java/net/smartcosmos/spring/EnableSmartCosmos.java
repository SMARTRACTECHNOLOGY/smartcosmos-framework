package net.smartcosmos.spring;

import java.lang.annotation.*;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Facilitates configuring a Smart Cosmos Service with Spring Configuration. This covers
 * only the basics of configuration, designed to be used in all services regardless of
 * whether they are SERVICE Extensions or SERVER Extensions.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableDiscoveryClient
@Documented
@Import({ SmartCosmosConfiguration.class })
public @interface EnableSmartCosmos {
}
