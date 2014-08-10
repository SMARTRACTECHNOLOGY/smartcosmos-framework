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

package net.smartcosmos.profiles.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.profiles.model.context.ITagProperties;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;

public class TagProperties implements ITagProperties
{
    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> tagIdentifiers = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> verificationTypes = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> propertyNames = new ArrayList<>();

    @Override
    public Collection<String> getTagIdentifiers()
    {
        return tagIdentifiers;
    }

    @Override
    public void setTagIdentifiers(Collection<String> tagIdentifiers)
    {
        Preconditions.checkNotNull(tagIdentifiers);
        this.tagIdentifiers.addAll(tagIdentifiers);
    }

    @Override
    public Collection<String> getVerificationTypes()
    {
        return verificationTypes;
    }

    @Override
    public void setVerificationTypes(Collection<String> verificationTypes)
    {
        Preconditions.checkNotNull(verificationTypes);
        this.verificationTypes.addAll(verificationTypes);
    }

    @Override
    public Collection<String> getPropertyNames()
    {
        return propertyNames;
    }

    @Override
    public void setPropertyNames(Collection<String> propertyNames)
    {
        Preconditions.checkNotNull(propertyNames);
        this.propertyNames.addAll(propertyNames);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagProperties that = (TagProperties) o;

        if (!propertyNames.equals(that.propertyNames)) return false;
        if (!tagIdentifiers.equals(that.tagIdentifiers)) return false;
        if (!verificationTypes.equals(that.verificationTypes)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tagIdentifiers.hashCode();
        result = 31 * result + verificationTypes.hashCode();
        result = 31 * result + propertyNames.hashCode();
        return result;
    }
}
