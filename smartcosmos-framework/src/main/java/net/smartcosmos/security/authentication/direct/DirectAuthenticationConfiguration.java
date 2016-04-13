package net.smartcosmos.security.authentication.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Provides easier access to direct authentication errors and success messages rather than
 * redirects.
 * <p>
 * This provides easier entry points for systems that do not provide a UI, or JavaScript
 * frameworks that rely on direct REST calls.
 * <p>
 * Code adapted from https://jhipster.github.io/
 *
 * @author voor
 * @see DirectAuthenticationSuccessHandler
 * @see DirectAuthenticationFailureHandler
 * @see DirectLogoutSuccessHandler
 * @see DirectUnauthorizedEntryPoint
 */
@ComponentScan
@Configuration
public class DirectAuthenticationConfiguration {

	@Autowired
	DirectAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	DirectAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	DirectLogoutSuccessHandler logoutSuccessHandler;

}
