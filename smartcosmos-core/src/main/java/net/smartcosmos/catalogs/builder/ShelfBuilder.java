

package net.smartcosmos.catalogs.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.Shelf;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class ShelfBuilder extends AbstractNamedObjectBuilder<IShelf, ShelfBuilder>
{
    private Collection<BookBuilder> builders = new ArrayList<>();

    public ShelfBuilder()
    {
        super(new Shelf());
    }

    public ShelfBuilder(ILibrary library)
    {
        super(new Shelf());

        Preconditions.checkNotNull(library);
        instance.setLibrary(library);
        instance.setAccount(library.getAccount());
    }

    public ShelfBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public ShelfBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public ShelfBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public ShelfBuilder addBook(BookBuilder builder)
    {
        Preconditions.checkNotNull(builder);
        builders.add(builder);
        return this;
    }

    public ShelfBuilder addBook(IBook book)
    {
        instance.addBook(book);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (BookBuilder builder : builders)
        {
            builder.setLibrary(instance.getLibrary());
            builder.setShelf(instance);
            addBook(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getLibrary(), "library must not be null");
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }

}
