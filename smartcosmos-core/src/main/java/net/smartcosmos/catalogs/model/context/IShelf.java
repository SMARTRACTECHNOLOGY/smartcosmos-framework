

package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import java.util.Collection;

public interface IShelf extends IAccountDomainResource<IShelf>, INamedObject<IShelf>, ITypedObject
{
    ILibrary getLibrary();

    void setLibrary(ILibrary library);

    IShelf addBook(IBook book);

    Collection<IBook> getBooks();
}
