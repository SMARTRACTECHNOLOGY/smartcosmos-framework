package net.smartcosmos.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.servlet.mvc.ServletWrappingController;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ MetricsServlet.class, ServletWrappingController.class })
@Conditional(MetricsConfiguration.PrometheusCondition.class)
@AutoConfigureBefore(ManagementWebSecurityAutoConfiguration.class)
@AutoConfigureAfter({ EmbeddedServletContainerAutoConfiguration.class })
@EnableConfigurationProperties({ PrometheusProperties.class, MetricsProperties.class })
public class MetricsConfiguration {

    public static final String PREFIX_ENDPOINTS = "endpoints";
    public static final String PREFIX_ENDPOINTS_PROMETHEUS = PREFIX_ENDPOINTS + ".prometheus";
    public static final String PREFIX_ENDPOINTS_METRICS = PREFIX_ENDPOINTS + ".metrics";
    public static final String ENABLED = "enabled";

    @Autowired(required = false)
    private Collection<PublicMetrics> publicMetrics;

    @Bean
    @ConditionalOnMissingBean
    @Autowired
    public PrometheusEndpoint prometheusEndpoint(PrometheusProperties properties) {

        return new PrometheusEndpoint(properties);
    }

    @Bean
    @Autowired
    public MetricsEndpoint metricsEndpoint(MetricsProperties properties) {

        List<PublicMetrics> publicMetrics = new ArrayList<>();
        if (this.publicMetrics != null) {
            publicMetrics.addAll(this.publicMetrics);
        }
        Collections.sort(publicMetrics, AnnotationAwareOrderComparator.INSTANCE);

        MetricsEndpoint metricsEndpoint = new MetricsEndpoint(publicMetrics);
        metricsEndpoint.setId(properties.getPath());
        metricsEndpoint.setEnabled(properties.getEnabled());
        metricsEndpoint.setSensitive(properties.getSensitive());

        return metricsEndpoint;
    }

    /**
     * Registers the spring boot metrics collector to grab spring boot metrics.
     */
    @Bean
    public SpringBootMetricsCollector springBootMetricsCollector() {

        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();

        return springBootMetricsCollector;
    }

    /**
     * Condition to check if Prometheus endpoint is enabled.
     */
    static class PrometheusCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

            boolean endpointsEnabled = isEnabled(context, PREFIX_ENDPOINTS, true);
            boolean enabled = isEnabled(context, PREFIX_ENDPOINTS_PROMETHEUS, endpointsEnabled);

            return new ConditionOutcome(enabled, "Prometheus enabled");
        }

        private boolean isEnabled(ConditionContext context, String prefix, boolean defaultValue) {

            Environment environment = context.getEnvironment();
            RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment, prefix);

            return resolver.getProperty(ENABLED, Boolean.class, defaultValue);
        }
    }
}
