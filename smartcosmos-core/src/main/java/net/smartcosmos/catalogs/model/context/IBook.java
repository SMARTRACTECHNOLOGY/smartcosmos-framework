

package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import java.util.Collection;

public interface IBook extends IAccountDomainResource<IBook>, INamedObject<IBook>, ITypedObject
{
    ILibrary getLibrary();

    void setLibrary(ILibrary library);

    IShelf getShelf();

    void setShelf(IShelf shelf);

    String getBookUrn();

    void setBookUrn(String bookUrn);

    IBook addChapter(IChapter chapter);

    Collection<IChapter> getChapters();
}
