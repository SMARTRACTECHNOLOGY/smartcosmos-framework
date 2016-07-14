package net.smartcosmos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import net.smartcosmos.security.authentication.OAuth2SsoRdaoConfiguration;
import net.smartcosmos.security.user.SmartCosmosUserConfiguration;

/**
 * Initiates the OAuth2 Single Sign-On capabilities of the SMART COSMOS Platform enables authentication through JWT at each service, rather than
 * requiring a stateful and centralized authentication process.  This will enable security on a service by default and additionally allows use of
 * {@link net.smartcosmos.security.user.SmartCosmosUser SmartCosmosUser} in Spring MVC methods.
 *
 * @see net.smartcosmos.security.user.SmartCosmosUser
 * @since 3.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ OAuth2SsoRdaoConfiguration.class, SmartCosmosUserConfiguration.class })
public @interface EnableSmartCosmosSecurity {
}
