package net.smartcosmos.security.authentication;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import net.smartcosmos.security.authentication.direct.DirectAccessDeniedHandler;
import net.smartcosmos.security.authentication.direct.DirectUnauthorizedEntryPoint;
import net.smartcosmos.security.authentication.direct.EnableDirectHandlers;

/**
 * @author voor
 */

@Configuration
@Slf4j
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2SsoRdaoConfiguration {

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
    @EnableDirectHandlers
    @EnableResourceServer
    @Configuration
    protected static class OAuth2SsoConfigurerAdapter
        extends ResourceServerConfigurerAdapter {

        @Autowired
        private Environment environment;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

            // This is necessary since it'll clear the context removing the mock user otherwise.
            // see https://stackoverflow.com/questions/37573361/springsecurity-withsecuritycontext-mockmvc-oauth2-always-unauthorised
            resources.stateless(!environment.acceptsProfiles("test"));
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            log.debug(
                    "Smart Cosmos Security enabled, all requests must be authorized and no login redirect is offered.");
            http.exceptionHandling().accessDeniedHandler(new DirectAccessDeniedHandler())
                    .authenticationEntryPoint(new DirectUnauthorizedEntryPoint("/login")).and()
                    .antMatcher("/**").authorizeRequests().anyRequest().authenticated();
        }
    }

}
