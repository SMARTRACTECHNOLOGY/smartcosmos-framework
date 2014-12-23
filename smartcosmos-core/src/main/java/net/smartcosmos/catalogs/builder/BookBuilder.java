

package net.smartcosmos.catalogs.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.Book;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class BookBuilder extends AbstractNamedObjectBuilder<IBook, BookBuilder>
{
    private Collection<ChapterBuilder> builders = new ArrayList<>();

    public BookBuilder()
    {
        super(new Book());
    }

    public BookBuilder(String bookUrn)
    {
        super(new Book());

        Preconditions.checkNotNull(bookUrn);
        instance.setBookUrn(bookUrn);
    }

    public BookBuilder(IShelf shelf)
    {
        super(new Book());
        instance.setLibrary(shelf.getLibrary());
        instance.setShelf(shelf);
        instance.setAccount(shelf.getAccount());
    }

    public BookBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public BookBuilder setShelf(IShelf shelf)
    {
        instance.setShelf(shelf);
        return this;
    }

    public BookBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public BookBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public BookBuilder addChapter(IChapter chapter)
    {
        instance.addChapter(chapter);
        return this;
    }

    public BookBuilder addChapter(ChapterBuilder builder)
    {
        Preconditions.checkNotNull(builder);
        builders.add(builder);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (ChapterBuilder builder : builders)
        {
            builder.setLibrary(instance.getLibrary());
            builder.setShelf(instance.getShelf());
            builder.setBook(instance);
            addChapter(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getLibrary());
        Preconditions.checkNotNull(instance.getShelf());
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }

}
