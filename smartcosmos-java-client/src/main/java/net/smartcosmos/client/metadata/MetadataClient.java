/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.smartcosmos.client.metadata;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpsertableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.endpoint.MetadataEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.context.Metadata;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

import static net.smartcosmos.Field.ENTITY_REFERENCE_TYPE;
import static net.smartcosmos.Field.KEY_FIELD;
import static net.smartcosmos.Field.REFERENCE_URN_FIELD;

class MetadataClient extends AbstractUpsertableBaseClient<IMetadata> implements IMetadataClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(MetadataClient.class);

    MetadataClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IMetadata findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, MetadataEndpoints.findByUrn(urn, viewType), Metadata.class);
    }

    @Override
    public ResponseEntity upsert(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(ENTITY_REFERENCE_TYPE));
        Preconditions.checkState(instance.has(REFERENCE_URN_FIELD));

        try
        {
            EntityReferenceType ert = EntityReferenceType.valueOf(instance.getString(ENTITY_REFERENCE_TYPE));
            JSONArray jsonArray = new JSONArray().put(instance);

            Collection<ResponseEntity> response = upsert(jsonArray, MetadataEndpoints.upsert(ert, instance.getString(REFERENCE_URN_FIELD)));

            if (response.size() != 1)
            {
                throw new ServiceException(new IllegalStateException("Response from server should have contained at least one ResponseEntry"));
            }
            return response.iterator().next();

        } catch (IllegalArgumentException | JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(IMetadata instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            delete(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(ENTITY_REFERENCE_TYPE));
        Preconditions.checkState(instance.has(REFERENCE_URN_FIELD));
        Preconditions.checkState(instance.has(KEY_FIELD));

        try
        {
            EntityReferenceType ert = EntityReferenceType.valueOf(instance.getString(ENTITY_REFERENCE_TYPE));
            String referenceUrn = instance.getString(REFERENCE_URN_FIELD);
            String key = instance.getString(KEY_FIELD);

            DeleteCommand command = new DeleteCommand(context);
            command.call(Object.class, MetadataEndpoints.delete(ert, referenceUrn, key));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public IMetadata findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key, ViewType viewType) throws ServiceException
    {
        GetCommand<IMetadata> command = new GetCommand<>(context);
        return command.call(Metadata.class, MetadataEndpoints.findSpecificKey(entityReferenceType, referenceUrn, key, viewType));
    }

    @Override
    public Collection<IMetadata> findAll(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IMetadata> command = new GetCollectionCommand<>(context);
        return command.call(Metadata.class, MetadataEndpoints.findAll(entityReferenceType, referenceUrn, viewType));
    }

    @Override
    public <T> String encodeMetadata(MetadataDataType metadataDataType, T instance) throws ServiceException
    {
        String encodedRawValue;

        ClientResource service = createClient(MetadataEndpoints.encodeMetadata(metadataDataType));

        try
        {
            Representation result = service.post(new StringRepresentation(instance.toString()));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();

            if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                encodedRawValue = jsonResult.getString(Field.RAW_VALUE_FIELD);
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                ResponseEntity response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return encodedRawValue;
    }

    @Override
    public JSONObject decodeMetadata(MetadataDataType metadataDataType, JSONObject jsonObject) throws ServiceException
    {
        JSONObject jsonResult;
        ClientResource service = createClient(MetadataEndpoints.decodeMetadata(metadataDataType));

        try
        {
            Representation result = service.post(new JsonRepresentation(jsonObject));
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            jsonResult = jsonRepresentation.getJsonObject();

            if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                return jsonResult;
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                ResponseEntity response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<IMetadata> findAll(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException
    {
        return findAll(entityReferenceType, referenceUrn, ViewType.Standard);
    }

    @Override
    public IMetadata findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key) throws ServiceException
    {
        return findSpecificKey(entityReferenceType, referenceUrn, key, ViewType.Standard);
    }
}
