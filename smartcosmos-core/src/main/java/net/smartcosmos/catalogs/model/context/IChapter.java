

package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;

import java.util.Collection;

public interface IChapter extends IAccountDomainResource<IChapter>, INamedObject<IChapter>
{
    ILibrary getLibrary();

    void setLibrary(ILibrary library);

    IShelf getShelf();

    void setShelf(IShelf shelf);

    IBook getBook();

    void setBook(IBook book);

    IChapter addChapterSection(IChapterSection chapterSection);

    Collection<IChapterSection> getChapterSections();
}
