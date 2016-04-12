package net.smartcosmos.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.HttpStatus;

/**
 * Provides an HTTP Method level enablement and role-based access control mechanism,
 * operating at a much finer level of detail than the <code>endpoints</code> configuration
 * found in the YML file.
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface EndpointMethodControl {
	/**
	 * Key within the <code>endpointMethodControl</code> section in the YML file that
	 * contains the Boolean flag that indicates if the associated HTTP Method is enabled
	 * (true) or disabled (false).
	 *
	 * @return endpointMethodControl key in the YML file
	 */
	String key();

	/**
	 * Flag the defines that the endpoint method control should enforce a role-based
	 * access control decision whereby the authenticated user <b>must</b> belong to <b>any
	 * 1 of the {@link #authorizedRoles()}</b> role types declared.
	 *
	 * @return defaults to false
	 */
	boolean checkRoles() default false;

	/**
	 * Identifies one or more roles, such that when {@link #checkRoles()} is set to
	 * <code>true</code> the authenticated user must belong to <i>at least 1 of the
	 * defined roles</i>; to be clear, this is <b>not an 'and' operation but an 'or'
	 * operation against this list!</b>
	 *
	 * @return no default defined
	 */
	String[] authorizedRoles() default {};

	/**
	 * HTTP Status code to return when the associated HTTP Method is either disabled or
	 * the authenticated user does not belong to the <b>any</b> of the
	 * {@link #authorizedRoles()} listed in the declaration.
	 *
	 * @return defaults to <b>UNAUTHORIZED</b>
	 */
	HttpStatus httpStatusCode() default HttpStatus.FORBIDDEN;

	/**
	 * Error code returned when the associated HTTP Method is either disabled or the
	 * authenticated user does not belong to <b>any</b> of the {@link #authorizedRoles()}
	 * listed in the declaration.
	 *
	 * @return defaults to -1 //ERR_UNAUTHORIZED
	 */
	int code() default -1;

	/**
	 * Error message returned when the associated HTTP Method is either disabled or the
	 * authenticated user does not belong to the <b>any</b> of the
	 * {@link #authorizedRoles()} listed in the declaration.
	 *
	 * @return defaults to "EndpointMethodControl Failure"
	 */
	String message() default "Use of this endpoint is forbidden on this server.";
}
