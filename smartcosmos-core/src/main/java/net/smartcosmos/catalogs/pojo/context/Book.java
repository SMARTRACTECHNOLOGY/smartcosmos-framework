package net.smartcosmos.catalogs.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    @JsonDeserialize(as = Library.class)
    protected ILibrary library;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Shelf.class)
    protected IShelf shelf;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(contentAs = Chapter.class)
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
