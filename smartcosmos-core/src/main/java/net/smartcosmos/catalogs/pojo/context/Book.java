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
import com.google.common.base.Preconditions;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Book extends AccountTypedNamedObject<IBook> implements IBook
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected String bookUrn;

    @JsonView(JsonGenerationView.Restricted.class)
    protected ILibrary library;

    @JsonView(JsonGenerationView.Restricted.class)
    protected IShelf shelf;

    @JsonView(JsonGenerationView.Minimum.class)
    protected Collection<IChapter> chapters = new ArrayList<>();

    @Override
    public ILibrary getLibrary()
    {
        return library;
    }

    @Override
    public void setLibrary(ILibrary library)
    {
        this.library = library;
    }

    @Override
    public void setShelf(IShelf shelf)
    {
        this.shelf = shelf;
    }

    @Override
    public IShelf getShelf()
    {
        return shelf;
    }

    @Override
    public String getBookUrn()
    {
        return bookUrn;
    }

    @Override
    public void setBookUrn(String bookUrn)
    {
        this.bookUrn = bookUrn;
    }

    @Override
    public IBook addChapter(IChapter chapter)
    {
        Preconditions.checkNotNull(chapter, "chapter must not be null");
        chapters.add(chapter);
        return this;
    }

    @Override
    public Collection<IChapter> getChapters()
    {
        return Collections.unmodifiableCollection(chapters);
    }
}
