package net.smartcosmos.metrics;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static net.smartcosmos.metrics.MetricsConfiguration.PREFIX_ENDPOINTS_METRICS;

/**
 * Properties for the Prometheus endpoint configuration.
 */
@ConfigurationProperties(prefix = PREFIX_ENDPOINTS_METRICS)
@Data
public class MetricsProperties {

    /**
     * Mark if the endpoint exposes sensitive information.
     */
    private Boolean sensitive = true;

    /**
     * Endpoint URL path.
     */
    @NotNull
    @Pattern(regexp = "/\\w+", message = "Path must start with '/' and must contain only letters, numbers and '_'")
    @Getter(AccessLevel.NONE)
    private String path = "/default_metrics";

    /**
     *
     */
    private Boolean enabled = false;

    public String getPath() {

        return path.replaceFirst("/", "");
    }
}
