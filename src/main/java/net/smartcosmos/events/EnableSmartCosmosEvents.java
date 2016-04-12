package net.smartcosmos.events;

import java.lang.annotation.*;

import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * @author voor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@IntegrationComponentScan(basePackageClasses = EnableSmartCosmosEvents.class)
public @interface EnableSmartCosmosEvents {
}
