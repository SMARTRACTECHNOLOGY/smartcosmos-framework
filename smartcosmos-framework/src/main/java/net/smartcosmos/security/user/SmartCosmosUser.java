package net.smartcosmos.security.user;

import java.util.Collection;
import java.util.Collections;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmartCosmosUser extends User {

    // region ANONYMOUS Default User

    /**
     * Internal key to identify if this object was set by an authorized client,
     * see documentation for {@link org.springframework.security.authentication.AnonymousAuthenticationToken}.
     */
    public static final String ANONYMOUS_AUTHENTICATION_KEY = "944d3969-8e68-49db-b200-50dd03d1169b";

    /**
     * User name for the ANONYMOUS user.
     */
    public static final String ANONYMOUS_USER_NAME = "ANONYMOUS";

    /**
     * Name of the ANONYMOUS user's role, used for the default authority {@code "hasRole('" + ANONYMOUS_USER_ROLE + "')"}.
     */
    public static String ANONYMOUS_USER_ROLE = "ROLE_ANONYMOUS";

    /**
     * The default authorities of the ANONYMOUS user.
     */
    public static Collection<GrantedAuthority> ANONYMOUS_USER_AUTHORITIES = Collections.singletonList(
        new SimpleGrantedAuthority("hasRole('" + ANONYMOUS_USER_ROLE + "')"));

    /**
     * An instance of {@link SmartCosmosUser} for the ANONYMOUS user.
     */
    public static SmartCosmosUser ANONYMOUS_USER = new SmartCosmosUser(null, null, ANONYMOUS_USER_NAME, "", ANONYMOUS_USER_AUTHORITIES);

    // endregion

    public final static String TOKEN_FIELD_URN_USER = "user_urn";
    public final static String TOKEN_FIELD_URN_ACCOUNT = "account_urn";

    private final String userUrn;

    private final String accountUrn;

    @JsonCreator
    public SmartCosmosUser(String accountUrn, String userUrn, String username, String password, Collection<? extends GrantedAuthority> authorities) {

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
