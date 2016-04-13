package net.smartcosmos.security.user;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * The Smart Cosmos User object is not actually used for authentication or authorization.
 * At this point, the JWT has already been issued and it is merely an exercise is
 * determining the approved authorized that this account has access to. This information
 * by default is stored in the JWT. Unfortunately, we also need to know the associated
 * AccountURN for the purposes of multi-tenancy. That's what this object represents, as
 * well as a future holder for customized details passed around the services.
 * <p>
 * The goal with this object is to support backwards compatibility through JSON.
 *
 * @author voor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmartCosmosUser extends User {
	public final static String TOKEN_FIELD_URN_USER = "user_urn";
	public final static String TOKEN_FIELD_URN_ACCOUNT = "account_urn";

	private final String userUrn;

	private final String accountUrn;

	@JsonCreator
	public SmartCosmosUser(String accountUrn, String userUrn, String username,
			String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userUrn = userUrn;
		this.accountUrn = accountUrn;
	}

	public String getUserUrn() {
		return this.userUrn;
	}

	public String getAccountUrn() {
		return accountUrn;
	}

}
