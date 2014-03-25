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

import com.snapbundle.client.impl.ICreateableBaseClient;
import com.snapbundle.client.impl.IDeleteableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IFile;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.restlet.data.MediaType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public interface IFileClient extends ICreateableBaseClient<IFile>, IDeleteableBaseClient<IFile>
{
    Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException;

    Collection<IFile> listOwnedBy(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException;

    ResponseEntity uploadOctetStream(String urn, File file, MediaType mediaType) throws ServiceException;

    ResponseEntity uploadAsMultiPartFormData(String urn, java.io.File file, MediaType mediaType) throws ServiceException;

    InputStream getFileContents(String urn) throws IOException;
}
