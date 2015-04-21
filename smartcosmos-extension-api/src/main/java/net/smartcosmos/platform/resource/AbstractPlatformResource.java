package net.smartcosmos.platform.resource;

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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
import io.dropwizard.views.View;
import net.smartcosmos.model.context.RoleType;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.IRequestHandler;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.util.TransactionException;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.ViewType;
import org.apache.shiro.authz.UnauthorizedException;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class AbstractPlatformResource<U extends ICosmosContext>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractPlatformResource.class);

    protected static final DateTimeFormatter RFC3339 = ISODateTimeFormat.dateTimeParser();

    protected final U context;

    protected AbstractPlatformResource(U context)
    {
        this.context = context;
    }

    protected <T> Response dispatchRequest(T inputValue, IRequestHandler<T> handler)
    {
        return dispatchRequest(inputValue, handler, null);
    }

    protected <T> Response dispatchTransactionalRequest(T inputValue,
                                           IRequestHandler<T> handler,
                                           IAuthenticatedUser authenticatedUser)
            throws TransactionException
    {
        return dispatchTransactionalRequest(inputValue, ViewType.Standard, handler, authenticatedUser);
    }

    protected <T> Response dispatchRequest(T inputValue,
                                           IRequestHandler<T> handler,
                                           IAuthenticatedUser authenticatedUser)
    {
        return dispatchRequest(inputValue, ViewType.Standard, handler, authenticatedUser);
    }

    protected void checkRole(RoleType roleType)
    {

    }

    protected <T> View dispatchViewRequest(T inputValue,
                                           IRequestHandler<T> handler,
                                           IAuthenticatedUser authenticatedUser)
    {
        View view;

        Preconditions.checkNotNull(handler, "Request handler must not be null");
        try
        {
            handler.increment();

            if (authenticatedUser != null)
            {
                handler.isAuthorized(authenticatedUser);

                checkRole(authenticatedUser.getRoleType());
            } else if (handler.forceAuthentication())
            {
                throw new WebApplicationException(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .header("Server", "SMART COSMOS")
                        .header("WWW-Authenticate", "Basic realm=\"SMART COSMOS Web Services Realm\"")
                        .type(MediaType.TEXT_HTML + ";charset=UTF-8")
                        .entity("<html>\n" +
                                "        <head>\n" +
                                "        <title>Status page</title>\n" +
                                "        </head>\n" +
                                "        <body style=\"font-family: sans-serif;\">\n" +
                                "        <p style=\"font-size: 1.2em;font-weight: bold;margin: 1em 0px;\">Un" +
                                "authorized</p>\n" +
                                "        <p>The request requires user authentication</p>\n" +
                                "        <p>You can get technical details <a href=\"http://" +
                                "www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.2\">here</a>.<br>\n" +
                                "            Please continue your visit at our <a href=\"/\">home page</a>.\n" +
                                "        </p>\n" +
                                "        </body>\n" +
                                "        </html>")
                        .build());
            }

            view = handler.render(inputValue, authenticatedUser);
        } catch (JsonProcessingException e)
        {
            LOG.warn(e.getMessage(), e);

            throw new WebApplicationException(Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseEntity.toJson(Result.ERR_FAILURE, e.getMessage()))
                    .build());

        } catch (UnauthorizedException e)
        {
            LOG.warn("Unauthorized operation attempt {}", e.getMessage());

            throw new WebApplicationException(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseEntity.toJson(Result.ERR_UNAUTHORIZED))
                    .build());

        } catch (WebApplicationException e)
        {
            throw e;
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);

            throw new WebApplicationException(Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseEntity.toJson(Result.ERR_INTERNAL, e.getMessage()))
                    .build());
        }

        return view;
    }


    protected <T> Response dispatchRequest(T inputValue,
                                           ViewType view,
                                           IRequestHandler<T> handler,
                                           IAuthenticatedUser authenticatedUser)
    {
        Response response;

        Preconditions.checkNotNull(handler, "Request handler must not be null");
        try
        {
            handler.increment();

            if (authenticatedUser != null)
            {
                handler.isAuthorized(authenticatedUser);

                checkRole(authenticatedUser.getRoleType());
            }

            response = handler.handle(inputValue, view, authenticatedUser);
        } catch (JsonProcessingException e)
        {
            LOG.warn(e.getMessage(), e);

            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseEntity.toJson(Result.ERR_FAILURE, e.getMessage()))
                    .build();

        } catch (UnauthorizedException e)
        {
            LOG.warn("Unauthorized operation attempt {}", e.getMessage());

            response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseEntity.toJson(Result.ERR_UNAUTHORIZED))
                    .build();

        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);

            response = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseEntity.toJson(Result.ERR_INTERNAL, e.getMessage()))
                    .build();
        }

        return response;
    }
    protected <T> Response dispatchTransactionalRequest(T inputValue,
                                           ViewType view,
                                           IRequestHandler<T> handler,
                                           IAuthenticatedUser authenticatedUser)
            throws TransactionException
    {
        Response response;

        Preconditions.checkNotNull(handler, "Request handler must not be null");
        try
        {
            handler.increment();

            if (authenticatedUser != null)
            {
                handler.isAuthorized(authenticatedUser);

                checkRole(authenticatedUser.getRoleType());
            }

            response = handler.handle(inputValue, view, authenticatedUser);
        } catch (JsonProcessingException e)
        {
            LOG.warn(e.getMessage(), e);

            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseEntity.toJson(Result.ERR_FAILURE, e.getMessage()))
                    .build();

        } catch (UnauthorizedException e)
        {
            LOG.warn("Unauthorized operation attempt {}", e.getMessage());

            response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseEntity.toJson(Result.ERR_UNAUTHORIZED))
                    .build();

        } catch (TransactionException e)
        {
            throw e;

        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);

            response = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseEntity.toJson(Result.ERR_INTERNAL, e.getMessage()))
                    .build();
        }

        return response;
    }
}
