package net.smartcosmos.events;

import java.lang.annotation.*;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @author voor
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@EnableSmartCosmosEvents
@EnableBinding(SmartCosmosEventSource.class)
public @interface SmartCosmosEventProducer {
}
