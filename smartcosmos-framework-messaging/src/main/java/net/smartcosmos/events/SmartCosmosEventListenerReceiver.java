package net.smartcosmos.events;

import java.lang.annotation.*;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * Indicates that a method is capable of receiving a Smart Cosmos Event Payload.
 * <p>
 * For more information see {@link ServiceActivator}
 *
 * @author voor
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@ServiceActivator(inputChannel = SmartCosmosEventSink.INPUT)
public @interface SmartCosmosEventListenerReceiver {
}
