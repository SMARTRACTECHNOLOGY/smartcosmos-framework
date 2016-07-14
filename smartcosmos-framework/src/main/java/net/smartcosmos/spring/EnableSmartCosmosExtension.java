package net.smartcosmos.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Defines the class packages that must implement the configuration necessary for a Smart Cosmos Extension to receive
 * component scanning during initialization.
 *
 * @see net.smartcosmos.annotation.EnableSmartCosmosExtension
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
@Deprecated
public @interface EnableSmartCosmosExtension {
}
