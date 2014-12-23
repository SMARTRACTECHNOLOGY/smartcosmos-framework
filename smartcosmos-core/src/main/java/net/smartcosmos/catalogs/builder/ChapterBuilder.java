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
