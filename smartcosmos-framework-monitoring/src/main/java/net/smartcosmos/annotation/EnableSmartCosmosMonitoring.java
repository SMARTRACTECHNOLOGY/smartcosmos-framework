package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import net.smartcosmos.config.MetricsConfiguration;

/**
 * <p>Enables Smart Cosmos Monitoring configuration that automatically adds a metrics management endpoint {@code /prometheus}. The endpoint is
 * compatible with Prometheus.</p>
 * <p>To be used on {@link org.springframework.context.annotation.Configuration Configuration} classes.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ MetricsConfiguration.class })
public @interface EnableSmartCosmosMonitoring {
}
