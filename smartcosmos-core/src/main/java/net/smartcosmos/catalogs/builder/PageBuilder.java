package net.smartcosmos.catalogs.builder;

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
import net.smartcosmos.builder.AbstractMonikerBuilder;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.IChapterSection;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IPage;
import net.smartcosmos.catalogs.model.context.IPageEntry;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.Page;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class PageBuilder extends AbstractMonikerBuilder<IPage, PageBuilder>
{
    private Collection<PageEntryBuilder> builders = new ArrayList<>();

    public PageBuilder()
    {
        super(new Page());
    }

    public PageBuilder(IChapterSection chapterSection)
    {
        super(new Page());
        instance.setLibrary(chapterSection.getLibrary());
        instance.setShelf(chapterSection.getShelf());
        instance.setBook(chapterSection.getBook());
        instance.setChapter(chapterSection.getChapter());
        instance.setChapterSection(chapterSection);
        instance.setAccount(chapterSection.getAccount());
    }

    public PageBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public PageBuilder setShelf(IShelf shelf)
    {
        instance.setShelf(shelf);
        return this;
    }

    public PageBuilder setBook(IBook book)
    {
        instance.setBook(book);
        return this;
    }

    public PageBuilder setChapter(IChapter chapter)
    {
        instance.setChapter(chapter);
        return this;
    }

    public PageBuilder setPageNumber(int pageNumber)
    {
        instance.setPageNumber(pageNumber);
        return this;
    }

    public PageBuilder setChapterSection(IChapterSection chapterSection)
    {
        instance.setChapterSection(chapterSection);
        return this;
    }

    public PageBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public PageBuilder addPageEntry(IPageEntry pageEntry)
    {
        instance.addPageEntry(pageEntry);
        return this;
    }

    public PageBuilder addPageEntry(PageEntryBuilder builder)
    {
        Preconditions.checkNotNull(builder);
        builders.add(builder);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (PageEntryBuilder builder : builders)
        {
            builder.setLibrary(instance.getLibrary());
            builder.setShelf(instance.getShelf());
            builder.setBook(instance.getBook());
            builder.setChapter(instance.getChapter());
            builder.setChapterSection(instance.getChapterSection());
            builder.setPage(instance);
            addPageEntry(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getLibrary());
        Preconditions.checkNotNull(instance.getShelf());
        Preconditions.checkNotNull(instance.getBook());
        Preconditions.checkNotNull(instance.getChapter());
        Preconditions.checkNotNull(instance.getChapterSection());
    }

}
