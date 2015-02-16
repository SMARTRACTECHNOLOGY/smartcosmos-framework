package net.smartcosmos.objects.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.objects.model.context.ITag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Convenience builder for assembling a collection of {@link net.smartcosmos.objects.model.context.ITag} instances
 * to define via a singular call.
 */
public final class TagCollectionBuilder
{
    Collection<ITag> tagCollection = new ArrayList<>();

    public TagCollectionBuilder addTag(String name)
    {
        tagCollection.add(new TagBuilder().setName(name).build());
        return this;
    }

    public Collection<ITag> build()
    {
        return Collections.unmodifiableCollection(tagCollection);
    }
}
