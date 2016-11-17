package net.smartcosmos.aspects;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import net.smartcosmos.exceptions.SmartCosmosException;

/**
 * Aspect to be applied to extension services implemented in the SMART COSMOS Objects architecture.
 * <p>
 * Using this aspect will capture all exceptions and convert them into a {@link SmartCosmosException}.  If your methods do not declare
 * they throw SmartCosmosException then you will receive an {@link UndeclaredThrowableException}
 * </p>
 * <p>
 * To use this aspect add the @SmartCosmosService annotation to the service class(es).  If you have interfaces with default methods
 * This annotation will need to be applied to the interface.
 * </p>
 * <p>For this aspect to be enabled you will need to enable aspects with @EnableAspectJAutoProxy in your @Configuration.  Spring Boot will
 * auto-configure this in most situations since at least 1.3.2.
 * </p>
 */
@Aspect
@Component
public class SmartCosmosServiceAspect {

    @Pointcut("@annotation(net.smartcosmos.annotation.SmartCosmosService) || @within(net.smartcosmos.annotation.SmartCosmosService)")
    public void hasSmartCosmosServiceAnnotation() {}

    @Pointcut("execution(public * *(..))")
    public void allPublicMethods() {}

    @Pointcut("hasSmartCosmosServiceAnnotation() && allPublicMethods()")
    public void allSmartCosmosServicePublicAnnotatedMethods() {}

    @AfterThrowing(pointcut = "allSmartCosmosServicePublicAnnotatedMethods()",
                   throwing = "t")
    public void handleServiceExceptions(JoinPoint jp, Throwable t) throws SmartCosmosException {

        if (t instanceof SmartCosmosException) {
            throw (SmartCosmosException) t;
        }

        String msg = String.format("Error in service: '%s', cause: '%s', method: '%s', arguments: '%s'",
                                   jp.getTarget()
                                       .toString(),
                                   t.toString(),
                                   jp.getSignature()
                                       .toLongString(),
                                   StringUtils.join(jp.getArgs(), " , "));

        if (t instanceof HttpStatusCodeException) {
            msg = msg.concat(String.format(", HTTP response body: '%s'", ((HttpStatusCodeException) t).getResponseBodyAsString()));
        }

        Logger.getLogger(jp.getClass())
            .warn(msg);
        Logger.getLogger(jp.getClass())
            .debug(msg, t);
        throw new SmartCosmosException(msg, t);
    }

}
