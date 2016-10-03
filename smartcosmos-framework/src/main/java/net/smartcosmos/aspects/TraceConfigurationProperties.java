package net.smartcosmos.aspects;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Dynamic configuration for tracing pointcut definitions.
 */
@Data
@ConfigurationProperties("smartcosmos.trace")
public class TraceConfigurationProperties {

    private String pointcut = "execution(* net.smartcosmos.*.*(..))";
}
