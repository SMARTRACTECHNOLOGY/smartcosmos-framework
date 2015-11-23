package ${package}.smartcosmos.extension.server.dao.impl;

import ${package}.smartcosmos.extension.server.dao.IExampleExtensionDAO;
import ${package}.smartcosmos.extension.server.jpa.IExampleEntity;
import ${package}.smartcosmos.extension.server.jpa.impl.ExampleEntity;
import net.smartcosmos.platform.api.dao.AbstractDAOImpl;
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
public class ExampleExtensionDAOImpl
        extends AbstractDAOImpl<IExampleEntity, ExampleEntity>
        implements IExampleExtensionDAO
{
    private SessionFactory sessionFactory;

    public ExampleExtensionDAOImpl(SessionFactory sessionFactory)
    {
        super(ExampleEntity.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection<IExampleEntity> findByFirstString(String firstString)
    {
        Collection<IExampleEntity> list = new ArrayList<>();

        Query listQuery = sessionFactory.getCurrentSession().createQuery("select e from example e " +
                                                                         "where e.firstString = :firstString")
                                        .setParameter("firstString", firstString);

        for (Object o : listQuery.list())
        {
            list.add((IExampleEntity) o);
        }
        return list;
    }


    @Override
    public Collection<IExampleEntity> findByBothStrings(String firstString, String secondString)
    {
        Collection<IExampleEntity> list = new ArrayList<>();

        Query listQuery = sessionFactory.getCurrentSession().createQuery("select e from example e " +
                                                                         "where e.firstString = :firstString " +
                                                                         "and e.secondString = :secondString")
                                        .setParameter("firstString", firstString)
                                        .setParameter("secondString", secondString);

        for (Object o : listQuery.list())
        {
            list.add((IExampleEntity) o);
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
