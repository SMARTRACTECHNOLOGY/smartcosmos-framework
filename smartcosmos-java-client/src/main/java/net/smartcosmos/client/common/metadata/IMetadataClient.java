package net.smartcosmos.client.common.metadata;

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

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.client.impl.IUpsertableBaseClient;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Defines, deletes, or queries for {@link net.smartcosmos.model.context.IMetadata} instances.
 */
public interface IMetadataClient extends IUpsertableBaseClient<IMetadata>, IDeleteableBaseClient<IMetadata>
{
    /**
     * Queries for a specific piece of metadata, by key name, under a specific entity type and reference URN, return the
     * match using a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param key                 Case-sensitive key name
     * @return Non-null metadata value
     * @throws ServiceException thrown if no such key under the specified entity type and reference URN exists
     */
    IMetadata findSpecificKey(EntityReferenceType entityReferenceType, String referenceUrn, String key)
            throws ServiceException;

    /**
     * Queries for a specific piece of metadata, by key name, under a specific entity type and reference URN, return the
     * match using the specified field verbosity.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param key                 Case-sensitive key name
     * @param viewType            Field verbosity
     * @return Non-null metadata value
     * @throws ServiceException thrown if no such key under the specified entity type and reference URN exists
     */
    IMetadata findSpecificKey(EntityReferenceType entityReferenceType,
                              String referenceUrn,
                              String key,
                              ViewType viewType) throws ServiceException;

    /**
     * Queries for all metadata keys under a specific entity type and reference URN, returned using
     * a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @return Non-null (but possibly empty) collection of metadata definitions
     * @throws ServiceException thrown if no such key under the specified entity type and reference URN exists
     */
    Collection<IMetadata> findAll(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException;

    /**
     * Queries for all metadata keys under a specific entity type and reference URN, returned using the specified field
     * verbosity.
     *
     * @param entityReferenceType Entity reference type
     * @param referenceUrn        Reference URN
     * @param viewType            Field verbosity
     * @return Non-null (but possibly empty) collection of metadata definitions
     * @throws ServiceException thrown if no such key under the specified entity type and reference URN exists
     */
    Collection<IMetadata> findAll(EntityReferenceType entityReferenceType,
                                  String referenceUrn,
                                  ViewType viewType) throws ServiceException;

    /**
     * Takes a type-safe object instance and submits it to the platform where an opaque encoding is applied and
     * returned, suitable for inclusion as the {@link net.smartcosmos.Field#RAW_VALUE_FIELD} when
     * upserting metadata.
     * <p/>
     * <B>NOTE:</B> Publicly accessible method call that does not require authentication
     *
     * @param metadataDataType Declared data type of the instance being submitted for encoding
     * @param instance         Instance that is of the same data type as the enum; use String for
     *                         {@link net.smartcosmos.model.context.MetadataDataType#XMLType} and
     *                         {@link net.smartcosmos.model.context.MetadataDataType#JSONType}
     * @param <T>              Instance that matches the data type defined by the metadataDataType enum
     * @return Encoded representation in JSON of the data provided,
     * accessible via the {@link net.smartcosmos.Field#RAW_VALUE_FIELD}
     * @throws ServiceException
     */
    <T> String encodeMetadata(MetadataDataType metadataDataType, T instance) throws ServiceException;

    /**
     * Takes a raw value byte array previously encoded by
     * {@link #encodeMetadata(net.smartcosmos.model.context.MetadataDataType, Object)} and decodes the value in a
     * type-safe manner.
     *
     * @param metadataDataType Declared data type of the {@link net.smartcosmos.Field#RAW_VALUE_FIELD} being submitted
     *                         for decoding
     * @param jsonObject       JSON object that contains the {@link net.smartcosmos.Field#RAW_VALUE_FIELD}
     * @return Type-safe decoded value extracted form the JSON object
     * @throws ServiceException
     */
    JSONObject decodeMetadata(MetadataDataType metadataDataType, JSONObject jsonObject) throws ServiceException;
}
