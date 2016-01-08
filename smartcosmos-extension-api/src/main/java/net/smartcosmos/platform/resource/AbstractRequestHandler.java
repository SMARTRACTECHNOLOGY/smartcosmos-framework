package net.smartcosmos.platform.resource;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.dropwizard.views.View;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.base.IMoniker;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.IRequestHandler;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.visitor.IVisitable;
import net.smartcosmos.platform.api.visitor.IVisitor;
import net.smartcosmos.platform.exception.mapper.SmartCosmosConstraintViolationExceptionMapper;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.ViewType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static net.smartcosmos.Field.MONIKER_FIELD;
import static net.smartcosmos.Field.NULL_MONIKER;

public abstract class AbstractRequestHandler<T> implements IRequestHandler<T>
{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRequestHandler.class);

    protected static final DateTimeFormatter ISO8601 = ISODateTimeFormat.dateTime();

    private final AtomicLong counter;

    protected final IContext context;

    protected static final List<EntityReferenceType> BINDABLE_ENTITIES = Arrays.asList(EntityReferenceType.Object,
            EntityReferenceType.ObjectAddress,
            EntityReferenceType.ObjectInteraction,
            EntityReferenceType.ObjectInteractionSession,
            EntityReferenceType.Georectification,
            EntityReferenceType.Device,
            EntityReferenceType.File,
            EntityReferenceType.Timeline,
            EntityReferenceType.LibraryElement);

    protected AbstractRequestHandler(final IContext context)
    {
        counter = new AtomicLong();
        this.context = context;
    }

    protected static final Response NO_SUCH_URN = Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .entity(ResponseEntity.toJson(Result.ERR_NO_SUCH_URN))
            .build();

    protected  static final Response EMPTY_REQUEST_BODY = Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .entity(ResponseEntity.toJson(Result.ERR_EMPTY_REQUEST))
            .build();

    protected static final Response NO_CONTENT = Response.noContent().build();

    /**
     * TODO: Decide whether stay with 400 BAD REQUEST or switch to the actually correct 422 UNPROCESSABLE ENTITY.
     * author: asiegel date: 26 Okt 2015
     */

    protected static final Response FIELD_CONSTRAINT_VIOLATION = Response
            /*
             * would be the actually correct response code but we don't use it at the moment to avoid breaking the API
             */
            // .status(org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY)
            .status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE).build();

    protected static final Response VALIDATION_FAILURE = Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .entity(ResponseEntity.toJson(Result.ERR_VALIDATION))
            .build();

    /**
     * A successfully authenticated user is impersonating another account. In a multi tenant system we want to make sure
     * we're acting on the correct IAccount, so we need to exchange the authenticated user for the IUser account they
     * are acting on behalf of.
     *
     * @param authenticatedUser
     *            who actually authenticated
     * @return the IUser, and therefore IAccount, that we want to actively work as.
     */
    protected abstract IUser exchangeForActual(IAuthenticatedUser authenticatedUser);

    @Override
    public boolean forceAuthentication()
    {
        return false;
    }

    @Override
    public long count()
    {
        return counter.get();
    }

    @Override
    public long increment()
    {
        return counter.incrementAndGet();
    }

    @Override
    public boolean isAuthorized(final IAuthenticatedUser authenticatedUser)
    {
        return true;
    }

    @Override
    public Response handle(final T inputValue, final IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        return handle(inputValue, ViewType.Standard, authenticatedUser);
    }

    @Override
    public Response handle(final T inputValue, final ViewType view, final IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        throw new WebApplicationException(Response.status(Response.Status.GONE).build());
    }

    @Override
    public View render(final T inputValue, final IAuthenticatedUser authenticatedUser)
            throws JsonProcessingException, JSONException
    {
        throw new WebApplicationException(Response.status(Response.Status.GONE).build());
    }

    /**
     * Parses a domain resource from the JSON input but does not validate it.
     *
     * @param jsonString
     *            the json input
     * @param targetClass
     *            the class used for mapping and validation
     * @return returns the validated domain resource
     * @throws WebApplicationException
     */
    public <ENTITY extends IDomainResource> ENTITY parseWithoutValidation(final String jsonString, final Class<ENTITY> targetClass)
            throws WebApplicationException
    {
        ENTITY entity = null;
        Response response = null;

        if (!jsonString.isEmpty())
        {
            try
            {
                entity = jsonToEntity(jsonString, targetClass);
            } catch (JsonParseException e)
            {
                LOG.warn(e.getMessage());
                response = Response.status(Response.Status.BAD_REQUEST)
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .entity(ResponseEntity.toJson(Result.ERR_FAILURE, "Parsing error: " + e.getMessage()))
                        .build();
            } catch (JsonMappingException e)
            {
                LOG.warn(e.getMessage());
                if (e instanceof InvalidFormatException)
                {
                    InvalidFormatException invalidFormatException = (InvalidFormatException) e;

                    if (invalidFormatException.getTargetType().equals(EntityReferenceType.class))
                    {
                        response = Response.status(Response.Status.BAD_REQUEST)
                                .type(MediaType.APPLICATION_JSON_TYPE)
                                .entity(ResponseEntity.toJson(Result.ERR_UNKNOWN_ENTITY_TYPE, invalidFormatException.getValue()))
                                .build();
                    }
                }

                if (response == null)
                {
                    response = VALIDATION_FAILURE;
                }
            } catch (IOException e)
            {
                LOG.warn(e.getMessage());
                response = VALIDATION_FAILURE;
            }
        } else
        {
            response = EMPTY_REQUEST_BODY;
        }

        if (response != null)
        {
            throw new WebApplicationException(response);
        }

        return entity;
    }

    public <ENTITY extends IDomainResource> List<ENTITY> parseListWithoutValidation(final String jsonString, final Class<ENTITY[]> targetClass)
    {
        List<ENTITY> resourceList = new ArrayList<>();
        Response response = null;

        if (!jsonString.isEmpty())
        {
            try
            {
                resourceList = jsonListToEntity(jsonString, targetClass);
            } catch (IOException e)
            {
                LOG.warn(e.getMessage());
                response = VALIDATION_FAILURE;
            }
        } else
        {
            response = EMPTY_REQUEST_BODY;
        }

        if (response != null)
        {
            throw new WebApplicationException(response);
        }

        return resourceList;
    }

    /**
     * Validates an input and maps it to the corresponding domain resource.
     *
     * @param jsonString
     *            the json input
     * @param targetClass
     *            the class used for mapping and validation
     * @return returns the validated domain resource
     * @throws WebApplicationException
     */
    public <ENTITY extends IDomainResource> ENTITY parse(final String jsonString, final Class<ENTITY> targetClass)
            throws WebApplicationException
    {
        ENTITY entity = parseWithoutValidation(jsonString, targetClass);
        validate(entity);

        return entity;
    }

    @VisibleForTesting
    public void updateMoniker(final JSONObject curObject, final IMoniker newValues, final IMoniker oldValues)
            throws JSONException
    {
        if (!curObject.has(MONIKER_FIELD) && oldValues == null)
        {
            newValues.setMoniker(null);
        } else if (!curObject.has(MONIKER_FIELD))
        {
            newValues.setMoniker(oldValues.getMoniker());
        } else if (curObject.has(MONIKER_FIELD) && curObject.getString(MONIKER_FIELD).equals(NULL_MONIKER))
        {
            newValues.setMoniker(null);
        } else
        {
            newValues.setMoniker(curObject.getString(MONIKER_FIELD));
        }
    }

    protected void updateMoniker(final JSONObject curObject, final IMoniker target) throws JSONException
    {
        if (!curObject.has(MONIKER_FIELD))
        {
            return;
        } else if (curObject.has(MONIKER_FIELD) && curObject.getString(MONIKER_FIELD).equals(NULL_MONIKER))
        {
            target.setMoniker(null);
        } else
        {
            target.setMoniker(curObject.getString(MONIKER_FIELD));
        }
    }

    protected EntityTag createETag(final IDomainResource domainResource)
    {
        return new EntityTag(domainResource.getClass()
                .getSimpleName()
                .concat("-" + domainResource.getUrn())
                .concat("-" + ISO8601.print(domainResource.getLastModifiedTimestamp())));
    }

    @SuppressWarnings("unchecked")
    protected void processVisitors(final EntityReferenceType entityReferenceType, final IVisitable instance)
    {
        Preconditions.checkNotNull(instance, "instance must not be null");
        Preconditions.checkNotNull(entityReferenceType, "entityReferenceType must not be null");

        SortedSet<IVisitor> visitors = context.getServiceFactory().getVisitorsForType(entityReferenceType);

        for (IVisitor visitor : visitors)
        {
            try
            {
                visitor.visit(instance);
            } catch (Exception e)
            {
                LOG.warn("Visitor {} (serviceId {}) threw an uncaught exception {}",
                        new Object[] {
                                visitor.getName(), visitor.getServiceId(), e.getMessage()
                        });
                LOG.debug(e.getMessage(), e);
            }
        }
    }

    protected CacheControl createStandardCacheControl()
    {
        CacheControl cc = new CacheControl();
        cc.setMaxAge((int) TimeUnit.SECONDS.convert(1, TimeUnit.DAYS));
        return cc;
    }

    private ObjectMapper createObjectMapper()
    {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        return om;
    }

    private <ENTITY extends IDomainResource> ENTITY jsonToEntity(final String jsonString, final Class<ENTITY> targetClass)
            throws IOException
    {
        ObjectMapper mapper = createObjectMapper();
        return mapper.readValue(jsonString, targetClass);
    }

    private <ENTITY extends IDomainResource> List<ENTITY> jsonListToEntity(final String jsonString, final Class<ENTITY[]> targetClass)
            throws IOException
    {
        ObjectMapper mapper = createObjectMapper();
        return Arrays.asList(mapper.readValue(jsonString, targetClass));
    }

    /**
     * Validates a domain resource.
     *
     * @param resource
     *            the domain resource resource for validation
     * @param <ENTITY>
     *            the sub-type of DomainResourceEntity
     * @throws WebApplicationException
     */
    public <ENTITY extends IDomainResource> void validate(final ENTITY resource) throws WebApplicationException
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        try
        {
            Set<ConstraintViolation<ENTITY>> violations = validator.validate(resource);
            if (!violations.isEmpty())
            {
                SmartCosmosConstraintViolationExceptionMapper exceptionMapper = new SmartCosmosConstraintViolationExceptionMapper();
                throw new WebApplicationException(exceptionMapper.toResponse(new ConstraintViolationException(violations)));
            }
        } catch (ValidationException e)
        {
            e.printStackTrace();
            throw new WebApplicationException(VALIDATION_FAILURE);
        }
    }
}
