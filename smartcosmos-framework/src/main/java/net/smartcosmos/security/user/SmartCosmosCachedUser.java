package net.smartcosmos.security.user;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.GrantedAuthority;

/**
 * Extends the default user while also adding in Smart Cosmos specific fields that need to
 * be added to the JWT.
 *
 * @author voor
 */
public class SmartCosmosCachedUser extends SmartCosmosUser {

    // Caching time is set in auth server config smartcosmos.security.cachedUserKeepAliveSecs
    private final Date cachedDate = new Date(System.currentTimeMillis());

    public SmartCosmosCachedUser(String accountUrn, String userUrn, String username,
            String password, Collection<? extends GrantedAuthority> authorities) {
        super(accountUrn, userUrn, username, password, authorities);
    }

    @Override
    public void eraseCredentials() {
        // do nothing here, just prevent the password hash erase implemented in the base class
    }

    public Date getCachedDate() {
        return this.cachedDate;
    }
}
