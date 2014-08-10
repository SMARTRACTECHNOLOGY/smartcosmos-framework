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
import net.smartcosmos.profiles.model.context.IVerifyTag;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;

public class VerifyTag implements IVerifyTag
{
    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> tagIdentifiers = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private String verificationType;

    @Override
    public Collection<String> getTagIdentifiers()
    {
        return tagIdentifiers;
    }

    @Override
    public void setTagIdentifiers(Collection<String> tagIdentifiers)
    {
        Preconditions.checkNotNull(tagIdentifiers);
        tagIdentifiers.addAll(tagIdentifiers);
    }

    @Override
    public String getVerificationType()
    {
        return verificationType;
    }

    @Override
    public void setVerificationType(String verificationType)
    {
        Preconditions.checkNotNull(verificationType);
        this.verificationType = verificationType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyTag that = (VerifyTag) o;

        if (!tagIdentifiers.equals(that.tagIdentifiers)) return false;
        if (!verificationType.equals(that.verificationType)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tagIdentifiers.hashCode();
        result = 31 * result + verificationType.hashCode();
        return result;
    }
}
