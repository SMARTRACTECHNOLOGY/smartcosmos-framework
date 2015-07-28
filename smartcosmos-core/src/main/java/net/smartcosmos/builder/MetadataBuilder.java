package net.smartcosmos.builder;

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

import com.google.common.base.Preconditions;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.pojo.context.Metadata;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.context.IMetadata} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#KEY_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#RAW_VALUE_FIELD}</li>
 * </ul>
 */
public final class MetadataBuilder extends AbstractReferentialBuilder<IMetadata, MetadataBuilder>
{
    public MetadataBuilder(MetadataDataType dataType)
    {
        super(new Metadata());
        instance.setDataType(dataType);
    }

    public MetadataBuilder setKey(String key)
    {
        instance.setKey(key);
        return this;
    }

    public MetadataBuilder setValue(String value)
    {
        instance.setValue(value);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getKey(), "key must not be null");
        Preconditions.checkNotNull(instance.getValue(), "value must not be null");
    }
}
