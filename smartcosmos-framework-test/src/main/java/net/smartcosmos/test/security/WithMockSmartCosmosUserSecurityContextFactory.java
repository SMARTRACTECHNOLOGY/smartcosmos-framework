package net.smartcosmos.test.security;

import java.util.HashSet;
import java.util.Set;

import org.mockito.internal.util.collections.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Factory for the security context with authenticated {@link SmartCosmosUser} for testing, see {@link WithMockSmartCosmosUser} annotation.
 */
public class WithMockSmartCosmosUserSecurityContextFactory implements WithSecurityContextFactory<WithMockSmartCosmosUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockSmartCosmosUser user) {

        String tenantUrn = user.tenantUrn();
        String userUrn = user.usernUrn();
        String username = user.username();
        String password = user.password();
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String authority : user.authorities()) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        // Get the client id
        final String clientId = user.clientId();
        // get the oauth scopes
        final Set<String> scopeCollection = Sets.newSet(user.scopes());

        SmartCosmosUser principal = new SmartCosmosUser(tenantUrn, userUrn, username, password, authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        // Create the authorization request and OAuth2Authentication object
        OAuth2Request authRequest = new OAuth2Request(null, clientId, null, true, scopeCollection, null, null, null,
                                                      null);
        OAuth2Authentication oAuth = new OAuth2Authentication(authRequest, authentication);

        // Add the OAuth2Authentication object to the security context
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(oAuth);

        return context;
    }
}
