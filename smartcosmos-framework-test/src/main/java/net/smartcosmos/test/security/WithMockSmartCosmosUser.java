package net.smartcosmos.test.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Adds a mocked {@link net.smartcosmos.security.user.SmartCosmosUser} instance to the security context. The principal will have a valid
 * {@link org.springframework.security.core.Authentication} based on the parameters defined with the annotation. By default, the user will have the
 * username {@code user} and password {@code password} without any authorities.
 * <p></p>
 * Example:
 * <pre>
 * {@code @WithMockSmartCosmosUser(authorities = {"https://authorities.smartcosmos.net/things/create"})}
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockSmartCosmosUserSecurityContextFactory.class)
public @interface WithMockSmartCosmosUser {

    /**
     * default value: {@code urn:tenant:uuid:b5b52af3-9909-4ea4-97bf-07639d683c95}
     * @return the mock user tenant URN
     */
    String tenantUrn() default "urn:tenant:uuid:b5b52af3-9909-4ea4-97bf-07639d683c95";

    /**
     * default value: {@code urn:user:uuid:e2174814-eb6d-4176-978c-58390105375b}
     * @return the mock user URN
     */
    String usernUrn() default "urn:user:uuid:e2174814-eb6d-4176-978c-58390105375b";

    /**
     * default value: {@code user}
     * @return the mock user username
     */
    String username() default "user";

    /**
     * default value: {@code password}
     * @return the mock user password
     */
    String password() default "password";

    /**
     * Example: {@code "https://authorities.smartcosmos.net/things/create"},
     * default: empty array
     * @return the mock user authorities
     */
    String[] authorities() default {};

    String[] scopes() default {};

    String clientId() default "smartcosmosservice";
}
