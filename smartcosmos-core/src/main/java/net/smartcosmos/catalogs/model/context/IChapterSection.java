

package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;

import java.util.Collection;

public interface IChapterSection extends IAccountDomainResource<IChapterSection>, INamedObject<IChapterSection>
{
    ILibrary getLibrary();

    void setLibrary(ILibrary library);

    IShelf getShelf();

    void setShelf(IShelf shelf);

    IBook getBook();

    void setBook(IBook book);

    IChapter getChapter();

    void setChapter(IChapter chapter);

    IChapterSection addPage(IPage page);

    Collection<IPage> getPages();

}
