package net.smartcosmos.metrics;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static net.smartcosmos.metrics.MetricsConfiguration.PREFIX_ENDPOINTS_PROMETHEUS;

/**
 * Properties for the Prometheus endpoint configuration.
 */
@ConfigurationProperties(prefix = PREFIX_ENDPOINTS_PROMETHEUS)
@Data
public class PrometheusProperties {

    /**
     * Name of the Prometheus Metrics servlet.
     */
    private String servletName = "prometheus";

    /**
     * Mark if the endpoint exposes sensitive information.
     */
    private Boolean sensitive = true;

    /**
     * Endpoint URL path.
     */
    @NotNull
    @Pattern(regexp = "/.*", message = "Path must start with /")
    private String path = "/metrics";
}
