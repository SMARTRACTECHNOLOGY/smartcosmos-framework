/*
 * SnapBundle™ SDK
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

package com.snapbundle.model.context;

import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.IReferentialObject;

/**
 * Specific instance of a type-safe key-value pair linked to a singular
 * referential object within the account's data set. Metadata types extend
 * beyond the expected primitive data types to more powerful composite types
 * like {@link com.snapbundle.model.context.MetadataDataType#JSONType} and
 * {@link com.snapbundle.model.context.MetadataDataType#XMLType}.
 * <p/>
 * Developers <b>must</b> rely on the platform-defined serialization rules
 * for every data type <i>except</i> {@link com.snapbundle.model.context.MetadataDataType#Custom}.
 */
public interface IMetadata extends IAccountDomainResource<IMetadata>, IReferentialObject
{
    MetadataDataType getDataType();

    void setDataType(MetadataDataType type);

    /**
     * Unique key within the {@link com.snapbundle.model.base.IReferentialObject} name space. In other
     * words, the key 'Foo' could be used for every single {@link com.snapbundle.model.context.IObject}
     * but cannot be used twice within the namespace defined by
     * {@link com.snapbundle.model.base.IReferentialObject#getReferenceUrn()}.
     *
     * @return Key name
     */
    String getKey();

    void setKey(String key);

    /**
     * Type-safe serialized representation of the key's value. Unless a
     * {@link com.snapbundle.model.context.MetadataDataType#Custom} is specified,
     * the serialization format must be treated as an opaque value that is encapsualted
     * by the platform.
     *
     * @return raw value
     */
    byte[] getRawValue();

    void setRawValue(byte[] value);
}