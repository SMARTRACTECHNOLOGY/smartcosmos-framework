package net.smartcosmos.platform.api.authentication;

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

import net.smartcosmos.model.context.IUser;

/**
 * Server-side representation of an {@link net.smartcosmos.model.context.IUser} that provides
 * full support for capturing an OAuth 2 authorized user acting on behalf of another account.
 */
public interface IAuthenticatedUser extends IUser
{
    /**
     * Flag to easily identify if the user was authorized to make this call using OAuth 2.0.
     *
     * @return true, if the user's authorization originated via an OAuth 2. bearer access token
     */
    boolean isOAuthAuthenticated();

    /**
     * Flag to easily identify if the user was authorized to make this call using HTTP BASIC authentication.
     *
     * @return true, if the user's authorization originated via an HTTP BASIC authorization header
     */
    boolean isBasicAuthenticated();

    /**
     * Extended user is the <i>actual account</i> making the call when {@link #isOAuthAuthenticated()} is
     * <code>true</code>.
     *
     * @return null when {@link #isOAuthAuthenticated()} is <code>false</code>, or the <i>actual</i> user when true
     */
    IUser getExtendedUser();
}
