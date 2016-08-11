package net.smartcosmos.security.authentication.direct;

import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configures direct authentication methods for easier error and redirect handling for a
 * REST service.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import(DirectAuthenticationConfiguration.class)
public @interface EnableDirectHandlers {
}
