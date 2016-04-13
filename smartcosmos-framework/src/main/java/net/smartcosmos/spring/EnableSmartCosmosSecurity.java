package net.smartcosmos.spring;

import java.lang.annotation.*;

import net.smartcosmos.security.authentication.OAuth2SsoRdaoConfiguration;
import net.smartcosmos.security.user.SmartCosmosUserConfiguration;

import org.springframework.context.annotation.Import;

/**
 * @author voor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ OAuth2SsoRdaoConfiguration.class, SmartCosmosUserConfiguration.class })
public @interface EnableSmartCosmosSecurity {
}
