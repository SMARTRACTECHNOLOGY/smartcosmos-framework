package ${package}.smartcosmos.extension.server.dao.impl;

import ${package}.smartcosmos.extension.server.dao.IMoreInterestingExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IMoreInterestingExampleEntity;
import ${package}.smartcosmos.extension.server.jpa.impl.MoreInterestingExampleEntity;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.api.dao.AbstractDAOImpl;
import net.smartcosmos.util.UuidUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collection;

// NOTE TO EXTENSION DEVELOPER:
// This DAO is registered with the context's DAOFactory in the registerResources() method of ExampleExtension.
// Unlike the core DAOs (object, metadata, relationship, ...), there is now method like:
// context.getDAOFactory().getExampleExtensionDAO()
// Instead, you have to use a lookup() method in DAOFactory. Examples of this cqn be found in any of the
// handler source files (e.g., ${package}.smartcosmos.extension.resource.pub.ExampleExtensionResource)
//
// To enable/disable deletion of these entities, see canDelete() below.
//
public class MoreInterestingExampleExtensionDAOImpl
        extends AbstractDAOImpl<IMoreInterestingExampleEntity, MoreInterestingExampleEntity>
        implements IMoreInterestingExampleExtensionDAO
{
    private SessionFactory sessionFactory;

    public MoreInterestingExampleExtensionDAOImpl(SessionFactory sessionFactory)
    {
        super(MoreInterestingExampleEntity.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection<IMoreInterestingExampleEntity> findByFirstString(String firstString, String referenceUrn,
                                                                       EntityReferenceType entityReferenceType,
                                                                       IAccount account)
    {
        Collection<IMoreInterestingExampleEntity> list = new ArrayList<>();

        Query listQuery = sessionFactory.getCurrentSession().createQuery("select e from moreInterestingExample e " +
                                                                         "where e.firstString = :firstString " +
                                                                         "and e.referenceUuid = :referenceUuid " +
                                                                         "and e.entityReferenceType = " +
                                                                         ":entityReferenceType " +
                                                                         "and e.account.systemUuid = :accountUuid")
                                        .setParameter("firstString", firstString)
                                        .setParameter("referenceUuid", UuidUtil.getUuidFromUrn(referenceUrn))
                                        .setParameter("entityReferenceType", entityReferenceType)
                                        .setParameter("accountUuid", account.getSystemUuid());

        for (Object o : listQuery.list())
        {
            list.add((IMoreInterestingExampleEntity) o);
        }
        return list;
    }


    @Override
    public Collection<IMoreInterestingExampleEntity> findByBothStrings(String firstString, String secondString,
                                                                       String referenceUrn,
                                                                       EntityReferenceType entityReferenceType,
                                                                       IAccount account)
    {
        Collection<IMoreInterestingExampleEntity> list = new ArrayList<>();

        Query listQuery = sessionFactory.getCurrentSession().createQuery("select e from moreInterestingExample e " +
                                                                         "where e.firstString = :firstString " +
                                                                         "and e.secondString = :secondString " +
                                                                         "and e.referenceUuid = :referenceUuid " +
                                                                         "and e.entityReferenceType = " +
                                                                         ":entityReferenceType " +
                                                                         "and e.account.systemUuid = :accountUuid")
                                        .setParameter("firstString", firstString)
                                        .setParameter("secondString", secondString)
                                        .setParameter("referenceUuid", UuidUtil.getUuidFromUrn(referenceUrn))
                                        .setParameter("entityReferenceType", entityReferenceType)
                                        .setParameter("accountUuid", account.getSystemUuid());

        for (Object o : listQuery.list())
        {
            list.add((IMoreInterestingExampleEntity) o);
        }
        return list;
    }

    // NOTE TO EXTENSION DEVELOPER:
    // If elements of this type should be immutable once written to the database, set this to false.
    //
    @Override
    public boolean canDelete()
    {
        return true;
    }
}
