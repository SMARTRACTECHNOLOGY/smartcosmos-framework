package net.smartcosmos.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import net.smartcosmos.security.authentication.OAuth2SsoRdaoConfiguration;
import net.smartcosmos.security.user.SmartCosmosUserConfiguration;

/**
 *
 * @see net.smartcosmos.annotation.EnableSmartCosmosSecurity
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ OAuth2SsoRdaoConfiguration.class, SmartCosmosUserConfiguration.class })
@Deprecated
public @interface EnableSmartCosmosSecurity {
}
