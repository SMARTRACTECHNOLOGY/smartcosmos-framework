package net.smartcosmos.security.user;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Custom version of {@link UserAuthenticationConverter} that stores user details directly
 * into the token.
 * <p>
 * This allows <b>non-private</b> information to be directly retrieved from the JWT,
 * providing for a truly stateless means of authentication exchange.
 *
 * @author voor
 */
@Component
public class SmartCosmosUserAuthenticationConverter
        implements UserAuthenticationConverter {

    private Collection<? extends GrantedAuthority> defaultAuthorities;


    /**
     * Default value for authorities if an Authentication is being created and the input
     * has no data for authorities. Note that unless this property is set, the default
     * Authentication created by {@link #extractAuthentication(Map)} will be
     * unauthenticated.
     *
     * @param defaultAuthorities the defaultAuthorities to set. Default null.
     */
    public void setDefaultAuthorities(String[] defaultAuthorities) {
        this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
    }

    public Map<String, ?> convertUserAuthentication(Authentication authentication) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null
                && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES,
                    AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        if (authentication.getPrincipal() instanceof SmartCosmosUser) {
            SmartCosmosUser user = (SmartCosmosUser) authentication.getPrincipal();
            response.put(SmartCosmosUser.TOKEN_FIELD_URN_ACCOUNT, user.getAccountUrn());
            response.put(SmartCosmosUser.TOKEN_FIELD_URN_USER, user.getUserUrn());
        }

        return response;
    }

    public Authentication extractAuthentication(Map<String, ?> map) {

        if (!map.containsKey(USERNAME)) {
            return null;
        }

        if (map.containsKey(SmartCosmosUser.TOKEN_FIELD_URN_ACCOUNT)
            && map.containsKey(SmartCosmosUser.TOKEN_FIELD_URN_USER)) {
            return getAuthenticationFromToken(map);
        } else {
            return getAuthenticationFromUserDetailsService(map);
        }
    }

    private Authentication getAuthenticationFromToken(Map<String, ?> map) {

        final String accountUrn = map.get(SmartCosmosUser.TOKEN_FIELD_URN_ACCOUNT)
            .toString();
        final String userUrn = map.get(SmartCosmosUser.TOKEN_FIELD_URN_USER)
            .toString();
        final String username = map.get(USERNAME)
            .toString();

        Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

        final SmartCosmosUser principal = new SmartCosmosUser(accountUrn, userUrn,
                                                              username, "N/A", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
    }

    private Authentication getAuthenticationFromUserDetailsService(Map<String, ?> map) {

        Object principal = map.get(USERNAME);
        Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

        return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        if (!map.containsKey(AUTHORITIES)) {
            return defaultAuthorities;
        }
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils
                    .commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException(
                "Authorities must be either a String or a Collection");
    }
}
