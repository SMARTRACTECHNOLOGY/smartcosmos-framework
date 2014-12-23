package net.smartcosmos.catalogs.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.catalogs.model.context.IBook;
import net.smartcosmos.catalogs.model.context.IChapter;
import net.smartcosmos.catalogs.model.context.IChapterSection;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IPage;
import net.smartcosmos.catalogs.model.context.IPageEntry;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.PageEntry;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IObject;

public class PageEntryBuilder extends AbstractNamedObjectBuilder<IPageEntry, PageEntryBuilder>
{
    public PageEntryBuilder()
    {
        super(new PageEntry());
    }

    public PageEntryBuilder(IPage page)
    {
        super(new PageEntry());
        instance.setLibrary(page.getLibrary());
        instance.setShelf(page.getShelf());
        instance.setBook(page.getBook());
        instance.setChapter(page.getChapter());
        instance.setChapterSection(page.getChapterSection());
        instance.setPage(page);
        instance.setAccount(page.getAccount());
    }

    public PageEntryBuilder setLibrary(ILibrary library)
    {
        instance.setLibrary(library);
        return this;
    }

    public PageEntryBuilder setShelf(IShelf shelf)
    {
        instance.setShelf(shelf);
        return this;
    }

    public PageEntryBuilder setBook(IBook book)
    {
        instance.setBook(book);
        return this;
    }

    public PageEntryBuilder setChapter(IChapter chapter)
    {
        instance.setChapter(chapter);
        return this;
    }

    public PageEntryBuilder setChapterSection(IChapterSection chapterSection)
    {
        instance.setChapterSection(chapterSection);
        return this;
    }

    public PageEntryBuilder setPage(IPage page)
    {
        instance.setPage(page);
        return this;
    }

    public PageEntryBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public PageEntryBuilder setObject(IObject object)
    {
        instance.setObject(object);
        return this;
    }

    public PageEntryBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");

        Preconditions.checkNotNull(instance.getLibrary());
        Preconditions.checkNotNull(instance.getShelf());
        Preconditions.checkNotNull(instance.getBook());
        Preconditions.checkNotNull(instance.getChapter());
        Preconditions.checkNotNull(instance.getChapterSection());
        Preconditions.checkNotNull(instance.getPage());
        Preconditions.checkNotNull(instance.getObject());
    }

}
