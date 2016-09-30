package net.smartcosmos.aspects;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import net.smartcosmos.exceptions.SmartCosmosException;

/**
 * Aspects to be applied to extension services implemented in the SMART COSMOS Objects architecture.
 */
@Aspect
@Component
public class SmartCosmosExtensionAspects {

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
                                   jp.getTarget(), t.toString(), jp.getSignature().toLongString(), StringUtils.join(jp.getArgs(), " , "));
        Logger.getLogger(jp.getClass()).error(msg, t);
        throw new SmartCosmosException(msg, t);
    }

}
