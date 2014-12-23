package net.smartcosmos.catalogs.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.IChapterSection;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IPage;
import net.smartcosmos.catalogs.model.context.IPageEntry;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class PageEntry extends AccountTypedNamedObject<IPageEntry> implements IPageEntry
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

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Page.class)
    protected IPage page;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = ObjectImpl.class)
    protected IObject object;

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
    public IPage getPage()
    {
        return page;
    }

    @Override
    public void setPage(IPage page)
    {
        this.page = page;
    }

    @Override
    public IObject getObject()
    {
        return object;
    }

    @Override
    public void setObject(IObject object)
    {
        this.object = object;
    }
}
