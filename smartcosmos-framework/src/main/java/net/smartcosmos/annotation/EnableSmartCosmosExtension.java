package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Defines the class packages that must implement the configuration necessary for a Smart Cosmos Extension to receive
 * component scanning during initialization.  This also
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration EnableAutoConfiguration} from Spring Boot to make sure that all necessary
 * beans are available.
 *
 * To be used on {@link org.springframework.context.annotation.Configuration Configuration} classes.
 *
 * @since 3.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@ComponentScan(basePackages = { "net.smartcosmos.dao", "net.smartcosmos.extension"})
@EnableAutoConfiguration
public @interface EnableSmartCosmosExtension {
}
