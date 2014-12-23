package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;

import java.util.Collection;

public interface IPage extends IAccountDomainResource<IPage>
{
    ILibrary getLibrary();

    void setLibrary(ILibrary library);

    IShelf getShelf();

    void setShelf(IShelf shelf);

    IBook getBook();

    void setBook(IBook book);

    IChapter getChapter();

    void setChapter(IChapter chapter);

    IChapterSection getChapterSection();

    void setChapterSection(IChapterSection chapterSection);

    int getPageNumber();

    void setPageNumber(int pageNumber);

    IPage addPageEntry(IPageEntry pageEntry);

    Collection<IPageEntry> getPageEntries();
}
