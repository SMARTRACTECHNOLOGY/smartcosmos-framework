package net.smartcosmos.security.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author voor
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class SmartCosmosUserConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    SmartCosmosUserArgumentResolver smartCosmosUserArgumentResolver;

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(smartCosmosUserArgumentResolver);
    }
}
