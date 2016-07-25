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
 * {@code @WithMockSmartCosmosUser(authorities = {"https://authorities.smartcosmos.net/things/write"})}
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockSmartCosmosUserSecurityContextFactory.class)
public @interface WithMockSmartCosmosUser {

    String tenantUrn() default "urn:tenant:uuid:b5b52af3-9909-4ea4-97bf-07639d683c95";

    String usernUrn() default "urn:user:uuid:e2174814-eb6d-4176-978c-58390105375b";

    String username() default "user";

    String password() default "password";

    String[] authorities() default {};
}
