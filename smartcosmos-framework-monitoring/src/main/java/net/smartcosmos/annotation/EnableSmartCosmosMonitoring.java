package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import net.smartcosmos.metrics.MetricsConfiguration;

/**
 * <p>Enables Smart Cosmos Monitoring configuration that automatically adds a metrics management endpoint which is compatible with Prometheus.The
 * default actuator endpoint is moved to {@code /default_metrics} then and will be disabled by default. The endpoint can also be completely removed
 * or enabled again by setting this accordingly:</p>
 * <pre>endpoints.metrics.enabled</pre>
 * <p>To be used on {@link org.springframework.context.annotation.Configuration Configuration} classes.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ MetricsConfiguration.class })
public @interface EnableSmartCosmosMonitoring {
}
