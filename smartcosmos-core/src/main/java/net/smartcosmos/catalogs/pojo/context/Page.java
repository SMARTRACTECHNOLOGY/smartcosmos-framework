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
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.IChapterSection;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IPage;
import net.smartcosmos.catalogs.model.context.IPageEntry;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.pojo.base.AccountDomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Page extends AccountDomainResource<IPage> implements IPage
{
    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Library.class)
    protected ILibrary library;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Shelf.class)
    protected IShelf shelf;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Book.class)
    protected IBook book;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Chapter.class)
    protected IChapter chapter;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = ChapterSection.class)
    protected IChapterSection chapterSection;

    @JsonView(JsonGenerationView.Minimum.class)
    protected Integer pageNumber;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(contentAs = PageEntry.class)
    protected Collection<IPageEntry> pageEntries = new ArrayList<>();

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
    public IBook getBook()
    {
        return book;
    }

    @Override
    public void setBook(IBook book)
    {
        this.book = book;
    }

    @Override
    public IChapter getChapter()
    {
        return chapter;
    }

    @Override
    public void setChapter(IChapter chapter)
    {
        this.chapter = chapter;
    }

    @Override
    public IChapterSection getChapterSection()
    {
        return chapterSection;
    }

    @Override
    public void setChapterSection(IChapterSection chapterSection)
    {
        this.chapterSection = chapterSection;
    }

    @Override
    public int getPageNumber()
    {
        return pageNumber;
    }

    @Override
    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    @Override
    public IPage addPageEntry(IPageEntry pageEntry)
    {
        Preconditions.checkNotNull(pageEntry, "pageEntry must not be null");
        pageEntries.add(pageEntry);
        return this;
    }

    @Override
    public Collection<IPageEntry> getPageEntries()
    {
        return Collections.unmodifiableCollection(pageEntries);
    }
}
