package net.smartcosmos.events;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author voor
 */
public class SendsSmartCosmosEventAdvice implements AfterReturningAdvice {

    private final SmartCosmosEventTemplate smartCosmosEventTemplate;

    @Autowired
    public SendsSmartCosmosEventAdvice(SmartCosmosEventTemplate smartCosmosEventTemplate) {
        this.smartCosmosEventTemplate = smartCosmosEventTemplate;
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if (returnValue instanceof SmartCosmosEvent) {
            smartCosmosEventTemplate.sendEvent((SmartCosmosEvent)returnValue);
        }
    }
}
