package net.smartcosmos.events;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Deprecated annotation to enable Smart Cosmos Events.  No longer necessary as long as new annotation is used.
 *
 * @see net.smartcosmos.annotation.EnableSmartCosmosEvents
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SmartCosmosEventConfiguration.class)
@Deprecated
public @interface EnableSmartCosmosEvents {
}
