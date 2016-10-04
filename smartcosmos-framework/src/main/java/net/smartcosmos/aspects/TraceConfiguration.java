package net.smartcosmos.aspects;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS;
import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_EXCEPTION;
import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_INVOCATION_TIME;
import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME;
import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_RETURN_VALUE;
import static org.springframework.aop.interceptor.CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME;

/**
 * A configuration used for AOP based method tracing.
 * <p>
 * Enable Spring AOP based tracing by enabling the "enableTracing" profile and enabling tracing in the logger definitions.
 * </p>
 * <p>
 * Creates a {@see CustomizableTraceInterceptor} with custom messages for entry, exit and exception and binds this to
 * a pointcut defined using <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html#aop-pointcuts">Spring
 * Pointcut documentation</a>.
 * </p>
 * <p>
 * Pointcuts are configured in {@see net.smartcosmos.aspects.TraceConfigurationProperties}.  The default pointput is
 * "execution(* net.smartcosmos.*.*(..))". Multiple pointcuts can be defined by combining pointcut definitions; e.g.
 * "execution(* net.smartcosmos.edge.bulkimport.*.*(..)) && execution(* net.smartcosmos.security.*.*(..))".
 * <p>
 * For more information see <a href="http://docs .spring.io/spring-framework/docs/current/spring-framework-reference/html/aop.html#aop-pointcuts
 * -combining">Spring AOP Combining pointcut expressions</a>.* </p>
 */
@Configuration
@Profile("enableTracing")
public class TraceConfiguration {

    TraceConfigurationProperties traceConfigurationProperties;

    @Autowired
    public TraceConfiguration(TraceConfigurationProperties traceConfigurationProperties) {

        this.traceConfigurationProperties = traceConfigurationProperties;
    }

    @Bean
    public CustomizableTraceInterceptor customizableTraceInterceptor() {

        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setUseDynamicLogger(true);
        customizableTraceInterceptor.setEnterMessage(getEnterMessage());
        customizableTraceInterceptor.setExitMessage(getExitMessage());
        customizableTraceInterceptor.setExceptionMessage(getExceptionMessage());
        return customizableTraceInterceptor;
    }

    @Bean
    public Advisor jpaRepositoryAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceConfigurationProperties.getPointcut());
        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
    }

    private String getEnterMessage() {

        return "Entering " + PLACEHOLDER_TARGET_CLASS_NAME + ":" + PLACEHOLDER_METHOD_NAME + "(" + PLACEHOLDER_ARGUMENTS + ").";
    }

    private String getExitMessage() {

        return "Exiting " + PLACEHOLDER_TARGET_CLASS_NAME + ":" +
               PLACEHOLDER_METHOD_NAME + "(" + PLACEHOLDER_ARGUMENTS +
               "), time: " + PLACEHOLDER_INVOCATION_TIME +
               ", return value: " + PLACEHOLDER_RETURN_VALUE + ".";
    }

    private String getExceptionMessage() {

        return "Exception thrown by " + PLACEHOLDER_TARGET_CLASS_NAME + ":" + PLACEHOLDER_METHOD_NAME + "(" + PLACEHOLDER_ARGUMENTS +
               "), cause: " + PLACEHOLDER_EXCEPTION + ".";
    }
}
