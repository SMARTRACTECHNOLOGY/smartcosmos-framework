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
 * Activated the security aspects of Smart Cosmos Objects.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ OAuth2SsoRdaoConfiguration.class, SmartCosmosUserConfiguration.class })
public @interface EnableSmartCosmosSecurity {
}
