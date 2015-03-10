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
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IOAuthTokenTransactionDAO;
import net.smartcosmos.platform.api.dao.ICosmosDAOFactory;
import net.smartcosmos.platform.api.oauth.IOAuthTokenTransaction;
import net.smartcosmos.platform.api.oauth.OAuthStatusType;
import net.smartcosmos.platform.api.service.IEventService;
import net.smartcosmos.platform.pojo.authentication.AuthenticatedUser;
import net.smartcosmos.platform.util.OAuthTokenUtil;
import net.smartcosmos.pojo.context.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatformOAuthAuthenticator<T extends ICosmosContext> implements Authenticator<String, IAuthenticatedUser>
{
    private static final Logger LOG = LoggerFactory.getLogger(PlatformOAuthAuthenticator.class);

    private final T context;

    public final class ExpiredTokenAccessAttempt
    {
        private IExtension extension;

        private String bearerAccessToken;

        private IEventService eventSinkImpersonated;

        private IEventService eventSinkExtension;

        private IUser extensionUser;

        private IUser impersonatedUser;

        private IOAuthTokenTransaction oauthTx;

        private IOAuthTokenTransactionDAO oAuthTokenTxDAO;

        public IExtension getExtension()
        {
            return extension;
        }

        public ExpiredTokenAccessAttempt setExtension(IExtension extension)
        {
            this.extension = extension;
            return this;
        }

        public String getBearerAccessToken()
        {
            return bearerAccessToken;
        }

        public ExpiredTokenAccessAttempt setBearerAccessToken(String bearerAccessToken)
        {
            this.bearerAccessToken = bearerAccessToken;
            return this;
        }

        public IEventService getEventSinkImpersonated()
        {
            return eventSinkImpersonated;
        }

        public ExpiredTokenAccessAttempt setEventSinkImpersonated(IEventService eventSinkImpersonated)
        {
            this.eventSinkImpersonated = eventSinkImpersonated;
            return this;
        }

        public IEventService getEventSinkExtension()
        {
            return eventSinkExtension;
        }

        public ExpiredTokenAccessAttempt setEventSinkExtension(IEventService eventSinkExtension)
        {
            this.eventSinkExtension = eventSinkExtension;
            return this;
        }

        public IUser getExtensionUser()
        {
            return extensionUser;
        }

        public ExpiredTokenAccessAttempt setExtensionUser(IUser extensionUser)
        {
            this.extensionUser = extensionUser;
            return this;
        }

        public IUser getImpersonatedUser()
        {
            return impersonatedUser;
        }

        public ExpiredTokenAccessAttempt setImpersonatedUser(IUser impersonatedUser)
        {
            this.impersonatedUser = impersonatedUser;
            return this;
        }

        public IOAuthTokenTransaction getOauthTx()
        {
            return oauthTx;
        }

        public ExpiredTokenAccessAttempt setOAuthTx(IOAuthTokenTransaction oauthTx)
        {
            this.oauthTx = oauthTx;
            return this;
        }

        public IOAuthTokenTransactionDAO getOAuthTokenTxDAO()
        {
            return oAuthTokenTxDAO;
        }

        public ExpiredTokenAccessAttempt setOAuthTokenTxDAO(IOAuthTokenTransactionDAO oAuthTokenTxDAO)
        {
            this.oAuthTokenTxDAO = oAuthTokenTxDAO;
            return this;
        }
    }

    public PlatformOAuthAuthenticator(T context)
    {
        this.context = context;
    }

    @Override
    public Optional<IAuthenticatedUser> authenticate(String bearerAccessToken) throws AuthenticationException
    {
        LOG.info("Attempting validate bearer access token {}", bearerAccessToken);

        ICosmosDAOFactory daoFactory = context.getDAOFactory();
        IOAuthTokenTransactionDAO oAuthTokenTransactionDAO = daoFactory.getOAuthTokenTransactionDAO();

        IOAuthTokenTransaction oauthTx = oAuthTokenTransactionDAO.findByBearerToken(bearerAccessToken);
        if (oauthTx != null)
        {
            LOG.debug("Successfully found oauth tx associated with bearer access token {}", bearerAccessToken);
            //
            // Token was found.
            //

            IExtension extension = oauthTx.getExtension();

            IUser extensionUser = new User();
            extensionUser.setGivenName(extension.getName());
            extensionUser.setSurname(extension.getAccount().getName());
            extensionUser.setEmailAddress(extension.getSupportEmail());
            extensionUser.setAccount(extension.getAccount());

            IUser impersonatedUser = oauthTx.getAuthorizingUser();

            IEventService eventSinkExtension = context.getServiceFactory().getEventService(extension.getAccount());
            IEventService eventSinkImpersonated = context.getServiceFactory()
                    .getEventService(impersonatedUser
                            .getAccount());

            // Is it still an active bearer token?
            if (oauthTx.getBearerTokenStatus() == OAuthStatusType.StaleToken_Expired ||
                    oauthTx.getBearerTokenStatus() == OAuthStatusType.StaleToken_Refreshed)
            {
                ExpiredTokenAccessAttempt attempt = new ExpiredTokenAccessAttempt()
                        .setExtension(extension)
                        .setBearerAccessToken(bearerAccessToken)
                        .setEventSinkExtension(eventSinkExtension)
                        .setEventSinkImpersonated(eventSinkImpersonated)
                        .setExtensionUser(extensionUser)
                        .setImpersonatedUser(impersonatedUser)
                        .setOAuthTx(oauthTx)
                        .setOAuthTokenTxDAO(oAuthTokenTransactionDAO);

                return logExpiredTokenAccessAttempt(context, attempt);
            } else if (oauthTx.getBearerTokenStatus() == OAuthStatusType.ActiveToken)
            {
                LOG.debug("bearer access token {} is still ACTIVE", bearerAccessToken);

                // Is it past its life expectancy?
                if (!OAuthTokenUtil.isBearerTokenExpired((ICosmosContext) context, oauthTx))
                {
                    LOG.debug("bearer access token {} is has NOT EXPIRED", bearerAccessToken);

                    if (context.getServiceFactory().getDirectoryService().isUserEnabled(impersonatedUser))
                    {
                        LOG.debug("bearer access token {} impersonated user {} is ENABLED",
                                bearerAccessToken,
                                impersonatedUser.getEmailAddress());

                        IAuthenticatedUser authenticatedUser = new AuthenticatedUser(impersonatedUser, extensionUser);
                        LOG.debug("Token exchanged successfully; impersonating username {}",
                                impersonatedUser.getEmailAddress());

                        eventSinkExtension.recordEvent(EventType.OAuthLoginSuccess,
                                extension.getAccount(),
                                extensionUser,
                                impersonatedUser);

                        eventSinkImpersonated.recordEvent(EventType.OAuthLoginSuccess,
                                oauthTx.getAuthorizingUser().getAccount(),
                                oauthTx.getAuthorizingUser(),
                                oauthTx.getExtension());


                        return Optional.of(authenticatedUser);
                    } else
                    {
                        LOG.info("bearer access token {} denied because {} account was marked disabled",
                                bearerAccessToken,
                                impersonatedUser.getEmailAddress());

                        // OAuth 2 was fine, but impersonated user account has been disabled
                        // since the authorization was granted
                        oauthTx.setBearerTokenStatus(OAuthStatusType.StaleToken_AuthorizingUserDisabled);
                        oAuthTokenTransactionDAO.update(oauthTx);

                        // As soon as we return absent(), Dropwizard will issue a rollback because it sees failure
                        context.getSessionFactory().getCurrentSession().getTransaction().commit();

                        eventSinkExtension.recordEvent(EventType.OAuthLoginFailureDisabledUser,
                                extension.getAccount(),
                                extensionUser,
                                impersonatedUser);

                        eventSinkImpersonated.recordEvent(EventType.OAuthLoginFailureDisabledUser,
                                oauthTx.getAuthorizingUser().getAccount(),
                                oauthTx.getAuthorizingUser(),
                                oauthTx.getExtension());

                        return Optional.absent();
                    }
                } else
                {
                    ExpiredTokenAccessAttempt attempt = new ExpiredTokenAccessAttempt()
                            .setExtension(extension)
                            .setBearerAccessToken(bearerAccessToken)
                            .setEventSinkExtension(eventSinkExtension)
                            .setEventSinkImpersonated(eventSinkImpersonated)
                            .setExtensionUser(extensionUser)
                            .setImpersonatedUser(impersonatedUser)
                            .setOAuthTx(oauthTx)
                            .setOAuthTokenTxDAO(oAuthTokenTransactionDAO);

                    return logExpiredTokenAccessAttempt(context, attempt);
                }
            } else
            {
                LOG.warn("bearer access token {} never existed in the database", bearerAccessToken);

                eventSinkExtension.recordEvent(EventType.OAuthLoginFailure,
                        extension.getAccount(),
                        extensionUser,
                        impersonatedUser);

                eventSinkImpersonated.recordEvent(EventType.OAuthLoginFailure,
                        oauthTx.getAuthorizingUser().getAccount(),
                        oauthTx.getAuthorizingUser(),
                        oauthTx.getExtension());

                return Optional.absent();
            }

        } else
        {
            LOG.warn("Bearer access token {} is not recognized", bearerAccessToken);

            IUser failedUser = new User();
            failedUser.setEmailAddress(bearerAccessToken);

            IEventService eventSink = context.getServiceFactory().getEventService((IAccount) null);

            //
            // EVENT: Login Failure
            //
            eventSink.recordEvent(EventType.OAuthLoginFailure, null, null, failedUser);

            return Optional.absent();
        }
    }

    private Optional<IAuthenticatedUser> logExpiredTokenAccessAttempt(T context,
                                                                      ExpiredTokenAccessAttempt attempt)
    {
        LOG.warn("bearer access token {} EXPIRED or REFRESHED and cannot be used", attempt.getBearerAccessToken());

        // Token has since expired and needs to be marked as such
        attempt.getOauthTx().setBearerTokenStatus(OAuthStatusType.StaleToken_Expired);
        attempt.getOAuthTokenTxDAO().update(attempt.getOauthTx());

        // As soon as we return absent(), Dropwizard will issue a rollback because it sees failure
        context.getSessionFactory().getCurrentSession().getTransaction().commit();

        attempt.getEventSinkExtension().recordEvent(EventType.OAuthLoginFailureStaleBearerAccessToken,
                attempt.getExtension().getAccount(),
                attempt.getExtensionUser(),
                attempt.getImpersonatedUser());

        attempt.getEventSinkImpersonated().recordEvent(EventType.OAuthLoginFailureStaleBearerAccessToken,
                attempt.getOauthTx().getAuthorizingUser().getAccount(),
                attempt.getOauthTx().getAuthorizingUser(),
                attempt.getOauthTx().getExtension());

        return Optional.absent();
    }
}


