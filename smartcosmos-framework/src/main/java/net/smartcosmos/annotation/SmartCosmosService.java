package net.smartcosmos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

/**
 * Annotation used with AOP to indicate the annotated element is capable of participating in AOP based exception handling.
 * <p>
 * When using the AOP based exception handling introduced when using this annotation all pbluc methods must throw only
 * {@see net.smartcosmos.exceptions.SmartCosmosException}.
 * </p>
 */
@Service
@Target({ ElementType.TYPE })
public @interface SmartCosmosService {
}
