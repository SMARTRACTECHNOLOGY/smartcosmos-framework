package net.smartcosmos.platform.api.annotation;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import net.smartcosmos.model.context.RoleType;

import javax.ws.rs.core.Response;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Provides an HTTP Method level enablement and role-based access control mechanism, operating at a much finer
 * level of detail than the <code>endpoints</code> configuration found in the YML file.
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface EndpointMethodControl
{
    /**
     * Key within the <code>endpointMethodControl</code> section in the YML file that contains the Boolean
     * flag that indicates if the associated HTTP Method is enabled (true) or disabled (false).
     *
     * @return endpointMethodControl key in the YML file
     */
    String key();

    /**
     * Flag the defines that the endpoint method control should enforce a role-based access control decision whereby
     * the authenticated user <b>must</b> belong to <b>any 1 of the {@link #authorizedRoles()}</b> role types declared.
     *
     * @return defaults to false
     */
    boolean checkRoles() default false;

    /**
     * Identifies one or more {@link RoleType} roles, such that when {@link #checkRoles()} is set to <code>true</code>
     * the authenticated user must belong to <i>at least 1 of the defined roles</i>; to be clear, this is <b>not
     * an 'and' operation but an 'or' operation against this list!</b>
     *
     * @return no default defined
     */
    RoleType[] authorizedRoles() default { };

    /**
     * HTTP Status code to return  when the associated HTTP Method is either disabled or the authenticated user does not
     * belong to the <b>any</b> of the {@link #authorizedRoles()} listed in the declaration.
     *
     * @return defaults to <b>UNAUTHORIZED</b>
     */
    Response.Status httpStatusCode() default Response.Status.UNAUTHORIZED;

    /**
     * Error code returned when the associated HTTP Method is either disabled or the authenticated user does not
     * belong to <b>any</b> of the {@link #authorizedRoles()} listed in the declaration.
     *
     * @return defaults to 0
     */
    int code() default 0;

    /**
     * Error message returned when the associated HTTP Method is either disabled or the authenticated user does not
     * belong to the <b>any</b> of the {@link #authorizedRoles()} listed in the declaration.
     *
     * @return defaults to "EndpointMethodControl Failure"
     */
    String message() default "EndpointMethodControl Failure";
}




