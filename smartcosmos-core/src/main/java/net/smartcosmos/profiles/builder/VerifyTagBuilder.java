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

package net.smartcosmos.profiles.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.profiles.model.context.IVerifyTag;
import net.smartcosmos.profiles.pojo.VerifyTag;

public class VerifyTagBuilder extends AbstractBuilder<IVerifyTag>
{
    public VerifyTagBuilder(String verificationType)
    {
        super(new VerifyTag());

        Preconditions.checkNotNull(verificationType);
        instance.setVerificationType(verificationType);
    }

    public VerifyTagBuilder addTagIdentifier(String tagIdentifier)
    {
        Preconditions.checkNotNull(tagIdentifier);
        instance.getTagIdentifiers().add(tagIdentifier);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkState(instance.getTagIdentifiers().size() > 0, "at least 1 tag identified must be defined");
    }
}


