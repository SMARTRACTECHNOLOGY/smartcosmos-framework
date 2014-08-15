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

package net.smartcosmos.catalogs.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Library extends AccountTypedNamedObject<ILibrary> implements ILibrary
{
    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(contentAs = Shelf.class)
    protected Collection<IShelf> shelves = new ArrayList<>();

    @Override
    public ILibrary addShelf(IShelf shelf)
    {
        Preconditions.checkNotNull(shelf, "shelf must not be null");
        shelves.add(shelf);
        return this;
    }

    @Override
    public Collection<IShelf> getShelves()
    {
        return Collections.unmodifiableCollection(shelves);
    }
}
