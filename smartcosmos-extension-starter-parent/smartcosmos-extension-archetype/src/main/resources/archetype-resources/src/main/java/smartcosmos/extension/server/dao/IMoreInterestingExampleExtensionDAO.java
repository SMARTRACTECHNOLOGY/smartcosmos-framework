package ${package}.smartcosmos.extension.server.dao;

import ${package}.smartcosmos.extension.server.jpa.IMoreInterestingExampleEntity;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.IBaseDAO;

import java.util.Collection;

public interface IMoreInterestingExampleExtensionDAO extends IBaseDAO<IMoreInterestingExampleEntity>
{

    // NOTE TO EXTENSION DEVELOPER:
    // Notice the lack of insert/update/delete methods? Find by URN and so on?
    // These are all taken care of for you in the base class AbstractDAOImpl,
    // from which any implementation of this interface must extend.
    //
    Collection<IMoreInterestingExampleEntity> findByFirstString(String firstString, String referenceUrn,
                                                                EntityReferenceType entityReferenceType,
                                                                IAccount account);

    Collection<IMoreInterestingExampleEntity> findByBothStrings(String firstString, String secondString,
                                                                String referenceUrn,
                                                                EntityReferenceType entityReferenceType,
                                                                IAccount account);
}
