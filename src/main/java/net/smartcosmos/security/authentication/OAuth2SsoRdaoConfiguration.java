package net.smartcosmos.security.authentication;

import lombok.extern.slf4j.Slf4j;
import net.smartcosmos.security.authentication.direct.DirectAccessDeniedHandler;
import net.smartcosmos.security.authentication.direct.DirectUnauthorizedEntryPoint;
import net.smartcosmos.security.authentication.direct.EnableDirectHandlers;
import net.smartcosmos.test.security.SmartCosmosTestProperties;
import net.smartcosmos.test.security.TestUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author voor
 */

@Configuration
@Slf4j
@ComponentScan
public class OAuth2SsoRdaoConfiguration {

	@Bean
	@Primary
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
	@EnableDirectHandlers
	@EnableResourceServer
	@Configuration
	@Profile({ "!test" })
	protected static class OAuth2SsoConfigurerAdapter
			extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.debug(
					"Smart Cosmos Security enabled, all requests must be authorized and no login redirect is offered.");
			http.exceptionHandling().accessDeniedHandler(new DirectAccessDeniedHandler())
					.authenticationEntryPoint(new DirectUnauthorizedEntryPoint()).and()
					.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
		}
	}

	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	@EnableDirectHandlers
	@Configuration
	@Profile({ "test" })
	@EnableConfigurationProperties({ SmartCosmosTestProperties.class })
	protected static class TestOAuth2SsoConfigurerAdapter
			extends WebSecurityConfigurerAdapter {

		@Autowired
		SmartCosmosTestProperties smartCosmosTestProperties;

		@Bean
		public UserDetailsService userDetailsService() {
			return new TestUserDetailsService(smartCosmosTestProperties.getUsers());
		}

		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
					.httpBasic();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			log.warn(
					"Test Smart Cosmos Security enabled, all requests should be made as user:password@ for testing.");
			auth.userDetailsService(userDetailsService())
					.passwordEncoder(new PlaintextPasswordEncoder());
		}
	}

}
