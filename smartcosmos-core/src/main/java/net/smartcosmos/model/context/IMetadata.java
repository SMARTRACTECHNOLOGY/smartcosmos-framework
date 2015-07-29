package net.smartcosmos.model.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;
import org.json.JSONException;

/**
 * Specific instance of a type-safe key-value pair linked to a singular
 * referential object within the account's data set. Metadata types extend
 * beyond the expected primitive data types to more powerful composite types
 * like {@link MetadataDataType#JSONType} and
 * {@link MetadataDataType#XMLType}.
 * <p/>
 * Developers <b>must</b> rely on the platform-defined serialization rules
 * for every data type <i>except</i> {@link MetadataDataType#Custom}.
 */
public interface IMetadata extends IAccountDomainResource<IMetadata>, IReferentialObject
{
    MetadataDataType getDataType();

    void setDataType(MetadataDataType type);

    /**
     * Unique key within the {@link net.smartcosmos.model.base.IReferentialObject} name space. In other
     * words, the key 'Foo' could be used for every single {@link net.smartcosmos.objects.model.context.IObject}
     * but cannot be used twice within the namespace defined by
     * {@link net.smartcosmos.model.base.IReferentialObject#getReferenceUrn()}.
     *
     * @return Key name
     */
    String getKey();

    void setKey(String key);

    /**
     * Type-safe serialized representation of the key's value. Unless a
     * {@link MetadataDataType#Custom} is specified,
     * the serialization format must be treated as an opaque value that is encapsualted
     * by the platform.
     *
     * @return raw value
     */
    String getRawValue();

    void setRawValue(String value);

    /**
     * Only included when <code>decoded=true</code> is added as a query string, in which case the decoded value is
     * automatically represented only as a String.
     *
     * @return decoded raw value as a string
     */
    String getDecodedValue() throws JSONException;
}
