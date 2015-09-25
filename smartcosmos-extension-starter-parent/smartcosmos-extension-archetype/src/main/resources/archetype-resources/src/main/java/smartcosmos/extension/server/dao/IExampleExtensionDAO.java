package ${package}.smartcosmos.extension.server.dao;

import ${package}.smartcosmos.extension.server.jpa.IExampleEntity;
import net.smartcosmos.platform.api.dao.IBaseDAO;

import java.util.Collection;

public interface IExampleExtensionDAO extends IBaseDAO<IExampleEntity>
{
    // NOTE TO EXTENSION DEVELOPER:
    // Notice the lack of insert/update/delete methods? Find by URN and so on?
    // These are all taken care of for you in the base class AbstractDAOImpl,
    // from which any implementation of this interface must extend.
    //

    Collection<IExampleEntity> findByFirstString(String firstString);
    Collection<IExampleEntity> findByBothStrings(String firstString, String secondString);
}
