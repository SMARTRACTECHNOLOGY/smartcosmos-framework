package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

/**
 * <p>Enables Smart Cosmos Monitoring configuration that automatically adds a metrics management endpoint which is compatible with Prometheus:
 * {@code /prometheus}.
 * <p>To be used on {@link org.springframework.context.annotation.Configuration Configuration} classes.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public @interface EnableSmartCosmosMonitoring {
}
