package net.smartcosmos.platform.authentication;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IUserDAO;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.pojo.context.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PlatformBasicAuthenticator<T extends ICosmosContext>
        implements Authenticator<BasicCredentials, IAuthenticatedUser>
{
    private static final Logger LOG = LoggerFactory.getLogger(PlatformBasicAuthenticator.class);

    private final T context;

    public PlatformBasicAuthenticator(T context)
    {
        this.context = context;
    }

    @SuppressWarnings("")
    @Override
    public Optional<IAuthenticatedUser> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        LOG.trace("Attempting to HTTP BASIC authenticate username {}", credentials.getUsername());

        try
        {
            IUserDAO userDAO = context.getDAOFactory().getUserDAO();

            IUser knownUser = userDAO.lookupEmailAddress(credentials.getUsername());
            LOG.debug("User with username {} is recognized", credentials.getUsername());


            IAuthenticatedUser authenticatedUser = context
                    .getServiceFactory()
                    .getDirectoryService()
                    .authenticate(new PlatformCredentials(credentials));

            //
            // EVENT: Login Success
            //
            IEventService eventSink = context.getServiceFactory().getEventService(knownUser.getAccount());
            eventSink.recordEvent(EventType.UserLoginSuccess, knownUser.getAccount(), knownUser, null);

            return Optional.of(authenticatedUser);
        } catch (Exception e)
        {
            LOG.warn("User with username {} is not recognized", credentials.getUsername());

            IUser failedUser = new User();
            failedUser.setEmailAddress(credentials.getUsername());

            //
            // EVENT: UserLoginFailure
            //
            IEventService eventSink = context.getServiceFactory().getEventService((IAccount) null);
            eventSink.recordEvent(EventType.UserLoginFailure, null, null, failedUser);

            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .header(HttpHeaders.WWW_AUTHENTICATE, "SMART COSMOS")
                    .entity("Credentials are required to access this resource.")
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());

//            return Optional.absent();
        }
    }
}

