package net.smartcosmos.platform.test.dao;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import io.dropwizard.hibernate.UnitOfWork;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.platform.dao.AbstractDAOImpl;

public abstract class AbstractNamedObjectDAOImplTest<S extends INamedObject<S>, T extends S, U extends AbstractDAOImpl<S, T>>
        extends AbstractDAOImplTest<S, T, U>
{

    @Test
    @Ignore
    @UnitOfWork
    public void testFindByNameLike()
    {
        fail("Not yet implemented");
    }

    @Test
    @Ignore
    @UnitOfWork
    public void testFindByNameExact()
    {
        fail("Not yet implemented");
    }

}
