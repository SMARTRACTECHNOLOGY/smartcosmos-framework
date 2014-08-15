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

package net.smartcosmos.catalogs.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.IChapterSection;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IPage;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.ChapterSection;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class ChapterSectionBuilder extends AbstractNamedObjectBuilder<IChapterSection, ChapterSectionBuilder>
{
    private Collection<PageBuilder> builders = new ArrayList<>();

    public ChapterSectionBuilder()
    {
        super(new ChapterSection());
    }

    public ChapterSectionBuilder(IChapter chapter)
    {
        super(new ChapterSection());
        instance.setLibrary(chapter.getLibrary());
        instance.setShelf(chapter.getShelf());
        instance.setBook(chapter.getBook());
        instance.setChapter(chapter);
        instance.setAccount(chapter.getAccount());
    }

    public ChapterSectionBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public ChapterSectionBuilder setShelf(IShelf shelf)
    {
        instance.setShelf(shelf);
        return this;
    }

    public ChapterSectionBuilder setBook(IBook book)
    {
        instance.setBook(book);
        return this;
    }

    public ChapterSectionBuilder setChapter(IChapter chapter)
    {
        instance.setChapter(chapter);
        return this;
    }

    public ChapterSectionBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public ChapterSectionBuilder addPage(IPage page)
    {
        instance.addPage(page);
        return this;
    }

    public ChapterSectionBuilder addPage(PageBuilder builder)
    {
        Preconditions.checkNotNull(builder);
        builders.add(builder);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (PageBuilder builder : builders)
        {
            builder.setLibrary(instance.getLibrary());
            builder.setShelf(instance.getShelf());
            builder.setBook(instance.getBook());
            builder.setChapter(instance.getChapter());
            builder.setChapterSection(instance);
            addPage(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getLibrary());
        Preconditions.checkNotNull(instance.getShelf());
        Preconditions.checkNotNull(instance.getBook());
        Preconditions.checkNotNull(instance.getChapter());
    }

}
