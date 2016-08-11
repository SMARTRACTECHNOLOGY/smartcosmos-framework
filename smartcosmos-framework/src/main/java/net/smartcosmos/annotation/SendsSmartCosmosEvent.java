package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that marks a method will return a SmartCosmosEvent that should be forwarded on to the necessary producer or event template interface
 * for sending {@code SmartCosmosEvent} classes.
 *
 * @see net.smartcosmos.events.SmartCosmosEvent
 * @see EnableSmartCosmosEvents
 * @see SendsSmartCosmosEventAnnotationBeanPostProcessor
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SendsSmartCosmosEvent {
}
