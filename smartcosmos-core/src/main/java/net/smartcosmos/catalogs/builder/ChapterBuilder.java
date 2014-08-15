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
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.Chapter;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class ChapterBuilder extends AbstractNamedObjectBuilder<IChapter, ChapterBuilder>
{
    private Collection<ChapterSectionBuilder> builders = new ArrayList<>();

    public ChapterBuilder()
    {
        super(new Chapter());
    }

    public ChapterBuilder(IBook book)
    {
        super(new Chapter());
        instance.setLibrary(book.getLibrary());
        instance.setShelf(book.getShelf());
        instance.setBook(book);
        instance.setAccount(book.getAccount());
    }

    public ChapterBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public ChapterBuilder setShelf(IShelf shelf)
    {
        instance.setShelf(shelf);
        return this;
    }

    public ChapterBuilder setBook(IBook book)
    {
        instance.setBook(book);
        return this;
    }

    public ChapterBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public ChapterBuilder addChapterSection(IChapterSection chapterSection)
    {
        instance.addChapterSection(chapterSection);
        return this;
    }

    public ChapterBuilder addChapterSection(ChapterSectionBuilder builder)
    {
        Preconditions.checkNotNull(builder);
        builders.add(builder);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (ChapterSectionBuilder builder : builders)
        {
            builder.setLibrary(instance.getLibrary());
            builder.setShelf(instance.getShelf());
            builder.setBook(instance.getBook());
            builder.setChapter(instance);
            addChapterSection(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getLibrary());
        Preconditions.checkNotNull(instance.getShelf());
        Preconditions.checkNotNull(instance.getBook());
    }

}
