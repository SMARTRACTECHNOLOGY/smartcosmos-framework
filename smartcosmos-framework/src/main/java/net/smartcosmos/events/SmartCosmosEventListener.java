package net.smartcosmos.events;

import java.lang.annotation.*;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * Denotes that the provided service class is capable of having methods that will receive
 * Smart Cosmos Events.
 *
 * @author voor
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@EnableSmartCosmosEvents
@EnableBinding(SmartCosmosEventSink.class)
public @interface SmartCosmosEventListener {
}
