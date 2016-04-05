package net.smartcosmos.client.objects.file;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.restlet.data.MediaType;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.ICreateableBaseClient;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;

/**
 * Defines, deletes, or queries for {@link net.smartcosmos.objects.model.context.IFile} instances and the actual
 * file contents.
 */
public interface IFileClient extends ICreateableBaseClient<IFile>, IDeleteableBaseClient<IFile>
{
    /**
     * Lists all {@link net.smartcosmos.objects.model.context.IFile} instances owned by the referential entity
     * identified by entityReferenceType and referenceUrn, returning all matches using a
     * {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        System-assigned reference URN
     * @return Non-null (but possibly empty) collection of files owned by the referential entity
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located
     */
    Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException;

    /**
     * Lists all {@link net.smartcosmos.objects.model.context.IFile} instances owned by the referential entity
     * identified by entityReferenceType and referenceUrn, returning all matches the specified field verbosity.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        System-assigned reference URN
     * @param viewType            Field verbosity
     * @return Non-null (but possibly empty) collection of files owned by the referential entity
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located
     */
    Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType,
                                  String referenceUrn,
                                  ViewType viewType) throws ServiceException;

    /**
     * Uploads a specific local file as an application/octet-stream. This is the preferred method for uploading a file's
     * actual contents to the platform.
     *
     * @param urn       System-assigned {@link net.smartcosmos.objects.model.context.IFile#getUrn()} with which this
     *                  file content is associated with
     * @param file      File to upload
     * @param mediaType Media type of the file being uploaded (must not be null)
     * @return Response entity indicating the success or failure of the operation
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located
     */
    ResponseEntity uploadOctetStream(String urn, File file, MediaType mediaType) throws ServiceException;

    /**
     * Uploads a specific local file as an application/octet-stream. This is the preferred method for uploading a file's
     * actual contents to the platform.
     *
     * @param urn         System-assigned {@link net.smartcosmos.objects.model.context.IFile#getUrn()} with which this
     *                    file content is associated with
     * @param inputStream Input stream to upload
     * @param mediaType   Media type of the file being uploaded (must not be null)
     * @return Response entity indicating the success or failure of the operation
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located
     */
    ResponseEntity uploadOctetStream(String urn, InputStream inputStream, MediaType mediaType) throws ServiceException;

    /**
     * Uploads a specific local file as an multipart/form-data. This capability is generally intended to be used by web
     * forms within a browser; it's inclusion in the API is for completeness. The recommended approach for uploading
     * a file's content from Java is {@link #uploadOctetStream(String, java.io.File, org.restlet.data.MediaType)}.
     *
     * @param urn       System-assigned {@link net.smartcosmos.objects.model.context.IFile#getUrn()} with which this
     *                  file content lis associated with
     * @param file      File to upload
     * @param mediaType Media type of the file being uploaded (must not be null)
     * @return Response entity indicating the success or failure of the operation
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located 
     */
    ResponseEntity uploadAsMultiPartFormData(String urn, java.io.File file, MediaType mediaType)
            throws ServiceException;

    /**
     * Retrieves the actual file contents associated with the file's system-assigned URN as an InputStream.
     *
     * @param urn System-assigned {@link net.smartcosmos.objects.model.context.IFile#getUrn()} with which this file
     *            content is associated with
     * @return Non-null input stream
     * @throws IOException
     * @throws ServiceException to indicate the file has no content or the URN specified cannot be located
     */
    InputStream getFileContents(String urn) throws ServiceException, IOException;
}
