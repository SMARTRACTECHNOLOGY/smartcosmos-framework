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

package com.snapbundle.client.metadata;

import com.snapbundle.client.api.IDeleteableBaseClient;
import com.snapbundle.client.api.IUpsertableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IMetadata;
import com.snapbundle.model.context.MetadataDataType;
import com.snapbundle.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

public interface IMetadataClient extends IUpsertableBaseClient<IMetadata>, IDeleteableBaseClient<IMetadata>
{
    IMetadata findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key) throws ServiceException;

    IMetadata findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key, ViewType viewType) throws ServiceException;

    Collection<IMetadata> findAll(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException;

    Collection<IMetadata> findAll(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException;

    <T> String encodeMetadata(MetadataDataType metadataDataType, T instance) throws ServiceException;

    JSONObject decodeMetadata(MetadataDataType metadataDataType, JSONObject jsonObject) throws ServiceException;
}
