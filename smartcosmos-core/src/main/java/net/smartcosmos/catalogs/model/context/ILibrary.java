

package net.smartcosmos.catalogs.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import java.util.Collection;

public interface ILibrary extends IAccountDomainResource<ILibrary>, INamedObject<ILibrary>, ITypedObject
{
    ILibrary addShelf(IShelf shelf);

    Collection<IShelf> getShelves();
}
