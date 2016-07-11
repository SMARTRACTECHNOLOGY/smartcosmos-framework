package net.smartcosmos.annotation;

import java.lang.reflect.Method;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import net.smartcosmos.events.SendsSmartCosmosEventAdvice;

/**
 * @author voor
 */
@Slf4j
public class SendsSmartCosmosEventAnnotationBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Autowired
    SendsSmartCosmosEventAdvice sendsSmartCosmosEventAdvice;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);

        Set<Method> methodsWithHandler = MethodIntrospector
            .selectMethods(targetClass,
                           (ReflectionUtils.MethodFilter) method -> AnnotationUtils.findAnnotation(method, SendsSmartCosmosEvent.class) != null);

        if (methodsWithHandler.size() > 0) {
            log.trace("Registering new listener for SendsSmartCosmosEvent at {}: {}", targetClass, beanName);
            AnnotationMatchingPointcut annotationMatchingPointcut = AnnotationMatchingPointcut.forMethodAnnotation(SendsSmartCosmosEvent.class);
            Advisor advisor = new DefaultPointcutAdvisor(annotationMatchingPointcut, sendsSmartCosmosEventAdvice);
            ProxyFactory factory;
            if (bean instanceof ProxyFactory) {
                factory = (ProxyFactory) bean;
            } else {
                factory = new ProxyFactory(bean);
            }
            factory.addAdvisor(advisor);
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
