package net.smartcosmos.events;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author voor
 */
@Slf4j
public class SendsSmartCosmosEventAdvice implements AfterReturningAdvice {

    private final SmartCosmosEventTemplate smartCosmosEventTemplate;

    @Autowired
    public SendsSmartCosmosEventAdvice(SmartCosmosEventTemplate smartCosmosEventTemplate) {
        this.smartCosmosEventTemplate = smartCosmosEventTemplate;
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.trace("Annotation driven interceptor is sending a {} event from {}",returnValue.getClass(),method.getName());
        if (returnValue instanceof SmartCosmosEvent) {
            smartCosmosEventTemplate.sendEvent((SmartCosmosEvent)returnValue);
        }
    }
}
