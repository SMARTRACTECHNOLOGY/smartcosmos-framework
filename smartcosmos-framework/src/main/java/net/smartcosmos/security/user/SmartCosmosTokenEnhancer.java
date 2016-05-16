package net.smartcosmos.security.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * Enhances the JWT Access Token to contain the AccountUrn and granted roles.
 *
 * @author voor
 * @see DefaultAccessTokenConverter
 * @see UserAuthenticationConverter
 * @see DefaultUserAuthenticationConverter
 * @see JwtAccessTokenConverter
 * @see JwtAccessTokenConverterConfigurer
 */
@Component
@Slf4j
public class SmartCosmosTokenEnhancer implements JwtAccessTokenConverterConfigurer {

    @Autowired
    public SmartCosmosTokenEnhancer(SmartCosmosUserAuthenticationConverter smartCosmosUserAuthenticationConverter) {
        this.smartCosmosUserAuthenticationConverter = smartCosmosUserAuthenticationConverter;
    }

    private final SmartCosmosUserAuthenticationConverter smartCosmosUserAuthenticationConverter;

    @Override
    public void configure(JwtAccessTokenConverter converter) {
        log.info(
                "Installing SmartCosmosUserAuthenticationConverter, will contain additional Smart Cosmos Account fields in JWT tokens.");
        ((DefaultAccessTokenConverter) converter.getAccessTokenConverter())
                .setUserTokenConverter(smartCosmosUserAuthenticationConverter);

    }
}
