package net.smartcosmos.platform.exception.mapper;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
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


import com.sun.jersey.api.ParamException;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>
{
    @Override
    public Response toResponse(WebApplicationException e)
    {
        Response response = e.getResponse();

        if (e instanceof ParamException)
        {
            response = paramException(e);
        }

        if (e.getResponse().getStatus() == Response.Status.UNAUTHORIZED.getStatusCode())
        {
            response = unauthorized(e);
        }

        return response;
    }

    private Response unauthorized(WebApplicationException e)
    {
        return Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(ResponseEntity.toJson(Result.ERR_UNAUTHORIZED))
                .build();
    }

    private Response paramException(WebApplicationException e)
    {
        Response response = e.getResponse();

        if (e.getCause() != null)
        {
            String exceptionCauseMessage = e.getCause().getMessage();

            if (e.getCause() instanceof IllegalArgumentException &&
                    exceptionCauseMessage.startsWith("No enum constant") && exceptionCauseMessage.contains("EntityReferenceType"))
            {
                String type = "";
                String message = e.getCause().getMessage();

                Pattern pattern = Pattern.compile("^.*\\.(.*)$");
                Matcher matcher = pattern.matcher(message);
                if (matcher.matches())
                {
                    type = matcher.group(1);
                }

                response = Response.status(Response.Status.BAD_REQUEST)
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .entity(ResponseEntity.toJson(Result.ERR_UNKNOWN_ENTITY_TYPE, type))
                        .build();
            }
        }

        return response;
    }
}
