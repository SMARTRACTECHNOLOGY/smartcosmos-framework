package net.smartcosmos.security;

import lombok.Data;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * Represents the configuration properties for Smart Cosmos Security.
 *
 * @author voor
 */
@Data
@ConfigurationProperties("smartcosmos.security.resource")
public class SecurityResourceProperties {

    public static final int DEFAULT_CACHED_USER_KEEP_ALIVE_SECS = 300;
    public static final int DEFAULT_ACCESS_TOKEN_VALIDITY_SECS = 60 * 60 * 12; // default 12 hours.
    public static final int DEFAULT_REFRESH_TOKEN_VALIDITY_SECS = 60 * 60 * 24 * 30; // default 30 days.

    private KeyStoreProperties keystore = new KeyStoreProperties();
    private UserDetailsProperties userDetails = new UserDetailsProperties();

    private String clientId;
    private String clientSecret;
    private Integer cachedUserKeepAliveSecs = DEFAULT_CACHED_USER_KEEP_ALIVE_SECS;
    private Integer accessTokenValiditySecs = DEFAULT_ACCESS_TOKEN_VALIDITY_SECS;
    private Integer refreshTokenValiditySecs = DEFAULT_REFRESH_TOKEN_VALIDITY_SECS;

    @Data
    public class KeyStoreProperties {
        private Resource location;
        private char[] password;
        private String keypair;
        private char[] keypairPassword;
    }

    @Data
    public class UserDetailsProperties {
        private SecurityProperties.User user = new SecurityProperties.User();
        private UserDetailsServerProperties server = new UserDetailsServerProperties();
    }

    @Data
    public class UserDetailsServerProperties {
        private String locationUri;
    }
}
