package net.smartcosmos.test.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import net.smartcosmos.security.user.SmartCosmosUser;

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

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        SmartCosmosUser principal = new SmartCosmosUser(tenantUrn, userUrn, username, password, authorities);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);

        return context;
    }
}
