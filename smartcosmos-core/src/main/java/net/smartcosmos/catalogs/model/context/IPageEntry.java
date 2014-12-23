package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;
import net.smartcosmos.objects.model.context.IObject;

/**
 * Use an {@link net.smartcosmos.objects.model.context.IRelationship} to create
 * a link between a Page Entry and a specific object. For example, if someone scanned an RFID
 * tag related to a Model ABC123 of an HVAC, then the page entry could
 * link to that {@link net.smartcosmos.objects.model.context.IObject} entry
 * to easily access all the metadata and multimedia files associated with that object.
 * <p/>
 * In essence, the IPageEntry is really just a pointer to a set of metadata; there is no text,
 * no content per se on the IPageEntry itself!
 */
public interface IPageEntry extends IAccountDomainResource<IPageEntry>, INamedObject<IPageEntry>, ITypedObject
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

    IPage getPage();

    void setPage(IPage page);

    IObject getObject();

    void setObject(IObject object);
}
