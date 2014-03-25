/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.client.file;

import com.google.common.base.Preconditions;
import com.snapbundle.Field;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.endpoint.FileEndpoints;
import com.snapbundle.client.impl.endpoint.RelationshipEndpoints;
import com.snapbundle.client.impl.base.AbstractCreateableBaseClient;
import com.snapbundle.client.impl.command.DeleteCommand;
import com.snapbundle.client.impl.command.GetCollectionCommand;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IFile;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.context.File;
import com.snapbundle.util.json.JsonUtil;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Disposition;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.html.FormData;
import org.restlet.ext.html.FormDataSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static com.snapbundle.Field.URN_FIELD;

class FileClient extends AbstractCreateableBaseClient<IFile> implements IFileClient
{
    private final static Logger LOGGER = LoggerFactory.getLogger(FileClient.class);

    FileClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IFile findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, FileEndpoints.findByUrn(urn, viewType), File.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, FileEndpoints.create());
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context);
            command.call(Object.class, RelationshipEndpoints.delete(instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(IFile instance) throws ServiceException
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
    public Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IFile> command = new GetCollectionCommand<>(context);
        return command.call(File.class, FileEndpoints.listFilesOwnedByEntity(entityReferenceType, referenceUrn, viewType));
    }

    @Override
    public ResponseEntity uploadOctetStream(String urn, java.io.File file, MediaType mediaType) throws ServiceException
    {
        ResponseEntity response;

        FileRepresentation fileRepresentation = new FileRepresentation(file, mediaType);
        fileRepresentation.setMediaType(MediaType.APPLICATION_OCTET_STREAM);

        ClientResource service = createClient(FileEndpoints.uploadContentsAsOctetStream(urn));
        service.accept(MediaType.APPLICATION_JSON);

        try
        {
            Representation result = service.post(fileRepresentation);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();
            response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

            if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.info("Successfully uploaded multipart-form data to path {}", FileEndpoints.uploadContentsAsMultipart(urn));
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public ResponseEntity uploadAsMultiPartFormData(String urn, java.io.File file, MediaType mediaType) throws ServiceException
    {
        ResponseEntity response;

        FileRepresentation fileRepresentation = new FileRepresentation(file, mediaType);

        ClientResource service = createClient(FileEndpoints.uploadContentsAsMultipart(urn));
        service.accept(MediaType.APPLICATION_JSON);

        FormDataSet form = new FormDataSet();
        form.setMultipart(true);
        form.getEntries().add(new FormData("file", fileRepresentation));
        form.add(Disposition.NAME_FILENAME, file.getName());

        Disposition disposition = new Disposition(Disposition.TYPE_INLINE);
        fileRepresentation.setDisposition(disposition);

        try
        {
            Representation result = service.post(form);
            JsonRepresentation jsonRepresentation = new JsonRepresentation(result);
            JSONObject jsonResult = jsonRepresentation.getJsonObject();
            response = JsonUtil.fromJson(jsonResult, ResponseEntity.class);

            if (service.getStatus().equals(Status.SUCCESS_OK))
            {
                LOGGER.info("Successfully uploaded multipart-form data to path {}", FileEndpoints.uploadContentsAsMultipart(urn));
            } else
            {
                LOGGER.error("Unexpected HTTP status code returned: {}", service.getStatus().getCode());
                throw new ServiceException(response);
            }

        } catch (JSONException | IOException e)
        {
            LOGGER.error("Unexpected Exception", e);
            throw new ServiceException(e);
        }

        return response;
    }

    @Override
    public InputStream getFileContents(String urn) throws IOException
    {
        ClientResource service = createClient(FileEndpoints.retrieveContents(urn));
        Representation representation = service.get();
        return representation.getStream();
    }

    @Override
    public Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException
    {
        return listOwnedBy(entityReferenceType, referenceUrn, ViewType.Standard);
    }
}
