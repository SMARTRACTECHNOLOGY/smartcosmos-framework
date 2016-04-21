package net.smartcosmos.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Defines the class packages that must implement the configuration necessary for a Smart Cosmos Extension to receive
 * component scanning during initialization.
 *
 * @author voor
 * @since 3.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@ComponentScan(basePackages = { "net.smartcosmos.dao", "net.smartcosmos.extension"})
@SpringBootApplication
public @interface EnableSmartCosmosExtension {
}
