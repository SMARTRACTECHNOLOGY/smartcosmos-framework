package net.smartcosmos.profiles.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
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

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.profiles.model.context.ITagProperties;
import net.smartcosmos.profiles.pojo.context.TagProperties;

import static com.google.common.base.Preconditions.checkState;

public class TagPropertiesBuilder extends AbstractBuilder<ITagProperties>
{
    public TagPropertiesBuilder()
    {
        super(new TagProperties());
    }

    public TagPropertiesBuilder addTagIdentifier(String tagIdentifier)
    {
        Preconditions.checkNotNull(tagIdentifier);
        instance.getTagIdentifiers().add(tagIdentifier);
        return this;
    }

    public TagPropertiesBuilder addPropertyName(String propertyName)
    {
        Preconditions.checkNotNull(propertyName);
        instance.getPropertyNames().add(propertyName);
        return this;
    }

    public TagPropertiesBuilder add(String verificationType)
    {
        Preconditions.checkNotNull(verificationType);
        instance.getVerificationTypes().add(verificationType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        checkState(instance.getTagIdentifiers().size() > 0, "at least 1 tag identified must be defined");
        checkState(instance.getVerificationTypes().size() > 0, "at least 1 verification type must be defined");
        checkState(instance.getPropertyNames().size() > 0, "at least 1 property name must be defined");
    }
}
