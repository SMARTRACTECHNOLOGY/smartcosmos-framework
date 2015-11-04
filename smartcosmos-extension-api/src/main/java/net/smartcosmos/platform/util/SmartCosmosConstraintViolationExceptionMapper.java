package net.smartcosmos.platform.util;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Objects JPA Mappings
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

import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SmartCosmosConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>
{
    private static final Logger LOG = LoggerFactory.getLogger(SmartCosmosConstraintViolationExceptionMapper.class);

    private int httpResponseStatus = HttpStatus.SC_UNPROCESSABLE_ENTITY;

    @Override
    public Response toResponse(ConstraintViolationException exception)
    {
        // Uncommented message and entity: detailed error message array

//        final ValidationErrorMessage message = new ValidationErrorMessage(exception.getConstraintViolations());
        String legacyMessage = errorMessageFields(exception.getConstraintViolations());

        return Response
                .status(httpResponseStatus)
                .type(MediaType.APPLICATION_JSON_TYPE)
//                .entity(ResponseEntity.toJson(Result.ERR_FIELD_CONSTRAINT_VIOLATION, message.getErrors()))
                .entity(ResponseEntity.toJson(Result.ERR_FIELD_CONSTRAINT_VIOLATION, legacyMessage))
                .build();
    }

    private String errorMessageFields(Set<ConstraintViolation<?>> violations)
    {
        String result = null;
        if (violations.size() > 0)
        {
            List<String> invalidFields = new ArrayList<>();
            String field;

            for (ConstraintViolation<?> violation : violations)
            {
                field = violation.getPropertyPath().toString();
                invalidFields.add(field);
                LOG.warn(field + " - " + violation.getMessage() + "\nInvalid value: " + violation.getInvalidValue());
            }

            result = StringUtils.join(invalidFields, ", ");
        }

        return result;
    }

    public void setHttpResponseStatus(int status)
    {
        this.httpResponseStatus = status;
    }
}
