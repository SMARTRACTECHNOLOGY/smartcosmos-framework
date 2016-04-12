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

	// TODO Caching time is hardcoded at 5 minutes, should change this.
	private final Date cachedDate = new Date(
			System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

	public SmartCosmosCachedUser(String accountUrn, String userUrn, String username,
			String password, Collection<? extends GrantedAuthority> authorities) {
		super(accountUrn, userUrn, username, password, authorities);
	}

	public Date getCachedDate() {
		return this.cachedDate;
	}
}
