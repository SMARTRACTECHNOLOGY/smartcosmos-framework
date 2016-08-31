package net.smartcosmos.config;

import java.util.Collection;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import net.smartcosmos.actuate.PrometheusMvcEndpoint;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ MetricsServlet.class, ServletWrappingController.class })
@Conditional(MetricsConfiguration.PrometheusCondition.class)
@AutoConfigureBefore(ManagementWebSecurityAutoConfiguration.class)
@AutoConfigureAfter(EmbeddedServletContainerAutoConfiguration.class)
public class MetricsConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PrometheusMvcEndpoint prometheusEndpoint() {

        return new PrometheusMvcEndpoint();
    }

    /**
     * Registers the spring boot metrics collector to grab spring boot metrics.
     */
    @Bean
    public SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {

        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();
        return springBootMetricsCollector;
    }

    /**
     * Condition to check that Prometheus endpoint is enabled.
     */
    static class PrometheusCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

            boolean endpointsEnabled = isEnabled(context, "endpoints.", true);
            boolean enabled = isEnabled(context, "endpoints.prometheus.", endpointsEnabled);
            return new ConditionOutcome(enabled, "Prometheus enabled");
        }

        private boolean isEnabled(ConditionContext context, String prefix, boolean defaultValue) {

            Environment environment = context.getEnvironment();
            RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment, prefix);
            boolean enabled = resolver.getProperty("enabled", Boolean.class, defaultValue);
            return enabled;
        }
    }
}
