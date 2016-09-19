package net.smartcosmos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

/**
 * Annotation use with AOP to indicate the annotated element is capavble of participating in AOP based exception handling.
 */
@Service
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface SmartCosmosService {
}
