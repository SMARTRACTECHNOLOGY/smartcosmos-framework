

package net.smartcosmos.catalogs.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Shelf extends AccountTypedNamedObject<IShelf> implements IShelf
{
    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Library.class)
    protected ILibrary library;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(contentAs = Book.class)
    protected Collection<IBook> books = new ArrayList<>();

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
    public IShelf addBook(IBook book)
    {
        Preconditions.checkNotNull(book, "book must not be null");
        books.add(book);
        return this;
    }

    @Override
    public Collection<IBook> getBooks()
    {
        return Collections.unmodifiableCollection(books);
    }
}
