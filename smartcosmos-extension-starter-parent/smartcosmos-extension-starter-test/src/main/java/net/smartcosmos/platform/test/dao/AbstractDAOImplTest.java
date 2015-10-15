/**
 * 
 */
package net.smartcosmos.platform.test.dao;

import static org.hamcrest.Matchers.greaterThan;
/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.integrator.spi.IntegratorService;
import org.hibernate.service.ServiceRegistry;
import org.hsqldb.jdbcDriver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.hibernate.UnitOfWork;
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.platform.api.dao.domain.IPage;
import net.smartcosmos.platform.dao.AbstractDAOImpl;
import net.smartcosmos.platform.jpa.integrator.PlatformHibernateIntegrator;

/**
 * This is a basic DAO test class that is included to assist in making sure your DAO implementations correspond to the
 * best practices and guidelines used elsewhere in Objects. The goal is by utilizing this test class in all your
 * implementations, other Extensions that leverage this DAO will be able to automagically include additional
 * functionality. The best example is the back-up and restore functionality in Objects.
 *
 */
public abstract class AbstractDAOImplTest<S extends IDomainResource<S>, T extends S, U extends AbstractDAOImpl<S, T>>
{

    @Rule
    public SessionFactoryRule sessionFactoryRule = new SessionFactoryRule(buildSessionFactory());

    /**
     * The DAO under test.
     */
    protected U dao;

    /**
     * Logging.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOImplTest.class);

    /**
     * These are entities required for the database to effectively run. The *goal* is to get this down to single
     * entities per DAO, as per DAO guidelines. Please note that the entity for the DAO itself does *not* need to be
     * included in this list, and is retrieved from {@code getEntityClass()}.
     * 
     * Initially, I can guarantee you'll need to add a few default Entities depending on what you extended for yours.
     * 
     * @see net.smartcosmos.platform.dao.AbstractDAOImpl
     * @see net.smartcosmos.platform.jpa.AccountEntity
     * @see net.smartcosmos.platform.jpa.BatchTransmissionEntity
     * @see net.smartcosmos.platform.jpa.EventEntity
     * @see net.smartcosmos.platform.jpa.UserEntity
     * @see net.smartcosmos.platform.jpa.base.DomainResourceAccountEntity
     * @see net.smartcosmos.platform.jpa.base.DomainResourceEntity
     * @see net.smartcosmos.platform.jpa.base.DomainResourceNamedObjectEntity
     * @see net.smartcosmos.platform.jpa.base.DomainResourceReferentialObjectEntity
     * 
     * @return list of entities.
     */
    protected abstract List<Class<?>> getEntities();

    /**
     * 
     * @return the JPA Annotated Entity Class that your DAO stores.
     */
    public abstract Class<T> getEntityClass();

    /**
     * 
     * @param identifier
     *            This is a string passed in to ascertain some sort of uniqueness from other than the ID. It's used in
     *            conjunction with the {@code getIdentifier} to facilitate checking entries for uniqueness. For example,
     *            say you created a Person and you want to make sure that 5 different people were added to the database.
     *            Identifier could be the last name, so that each last name can be checked to make sure it was
     *            successfully added.
     * @return a valid populated entity with all required fields added. Like if name is required, it's populated, or
     *         date, etc.
     * 
     */
    public abstract T getValidPopulatedEntity(String identifier);

    /**
     * 
     * This function is used in conjunction with the {@code getValidPopulatedEntity(String identifier)} function. The
     * idea behind this, is that function might store identifier into the Last Name field, and this function should
     * retrieve it from the last name field.
     * 
     * @param entity
     *            the entity to retrieve the identifier from.
     * @return the identifier that can be checked against testing.
     */
    public abstract String getIdentifier(S entity);

    /**
     * 
     * @return an invalid entity, like it's missing a required field, or even it's just null.
     */
    public abstract T getInvalidPopulatedEntity();

    /**
     * 
     * @return the DAO under test.
     */
    public U getDAO()
    {
        return dao;
    }

    /**
     * Bootstrapping process for creating the session factory, making sure the Hibernate Integrator is present.
     * 
     * @see PlatformHibernateIntegrator
     * @see org.hibernate.context.internal.ManagedSessionContext
     * 
     * @return created in-memory session factory.
     */
    protected SessionFactory buildSessionFactory()
    {
        try
        {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    // This makes sure we keep the org.hsqldb.jdbcDriver on the classpath.
                    .setProperty("hibernate.connection.driver_class", jdbcDriver.class.getName())
                    .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:testdb")
                    .setProperty("hibernate.hbm2ddl.auto", "create")
                    // This is very important, we utilized
                    .setProperty("hibernate.current_session_context_class", "managed");
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

            if (getEntities() != null)
            {
                for (Class<?> clazz : getEntities())
                {
                    configuration.addAnnotatedClass(clazz);
                }
            }

            assertNotNull(getEntityClass());

            configuration.addAnnotatedClass(getEntityClass());

            // Make sure this is applied, this is how we make sure everything new entry has a unique ID and URN
            // associated.
            final Integrator integrator = new PlatformHibernateIntegrator();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            serviceRegistryBuilder.addService(IntegratorService.class, new IntegratorService()
            {

                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;

                @Override
                public Iterable<Integrator> getIntegrators()
                {
                    LOG.debug("Adding in integrators.");
                    List<Integrator> integrators = new ArrayList<>();
                    integrators.add(integrator);
                    return integrators;
                }
            });
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

            Iterable<Integrator> applied = serviceRegistry.getService(IntegratorService.class).getIntegrators();
            // Like I said, very important this is there.
            assertNotNull(applied);

            // Like super important.
            assertEquals(integrator, applied.iterator().next());

            final SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Exception ex)
        {
            LOG.error("Initial SessionFactory creation failed." + ex);
            LOG.debug(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        this.dao = populateDAO();
    }

    /**
     * 
     * @return the newly created DAO using whatever constructor necessary. This function is called <b>after</b>
     *         {@code sessionFactory} is populated, so it can absolutely be used.
     */
    protected abstract U populateDAO();

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#getEntityClass()}.
     */
    @Test
    public void testGetEntityClass()
    {
        assertNotNull(dao.getEntityClass());
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#upsert(net.smartcosmos.model.base.IDomainResource)}.
     */
    @Test
    @UnitOfWork
    public void testUpsert()
    {

        final T entity = getValidPopulatedEntity("1");
        S inserted = dao.upsert(entity);

        assertNotNull(inserted);

        S result = dao.findByUrn(getEntityClass(), inserted.getUrn());

        assertNotNull(result);
        assertEquals(getIdentifier(entity), getIdentifier(result));
    }

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#count()}.
     */
    @Test
    @UnitOfWork
    public void testCount()
    {

        final Long initial = dao.count();

        final T entity = getValidPopulatedEntity("1");

        assertNotNull(entity);
        assertNull(entity.getUrn());

        S inserted = dao.insert(entity);

        final Long afterInsert = dao.count();

        assertThat(afterInsert, greaterThan(initial));

    }

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#page(int, int)}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testPage()
    {
        IPage<S> page = dao.page(0, 1);
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#insert(net.smartcosmos.model.base.IDomainResource)}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testInsert()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#canDelete()}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testCanDelete()
    {
        dao.canDelete();
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#delete(net.smartcosmos.model.base.IDomainResource)}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testDelete()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#update(net.smartcosmos.model.base.IDomainResource)}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testUpdate()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#findByUrn(java.lang.Class, java.lang.String, net.smartcosmos.model.context.IAccount)}
     * .
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testFindByUrnClassOfQStringIAccount()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#findByUrn(java.lang.Class, java.lang.String)}
     * .
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testFindByUrnClassOfQString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#findByUuids(java.util.Collection, net.smartcosmos.model.context.IAccount)}
     * .
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testFindByUuids()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#findByAccount(java.lang.Class, net.smartcosmos.model.context.IAccount)}
     * .
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testFindByAccount()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#searchByMoniker(java.lang.Class, java.lang.String, net.smartcosmos.model.context.IAccount)}
     * .
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testSearchByMoniker()
    {
        fail("Not yet implemented");
    }

    @Test
    @Ignore
    @UnitOfWork
    public void testSearchByMonikerLike()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link net.smartcosmos.platform.dao.AbstractDAOImpl#getPath()}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testGetPath()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link net.smartcosmos.platform.dao.AbstractDAOImpl#advancedQuery(com.querydsl.core.types.Predicate[])}.
     */
    @Test
    @Ignore
    @UnitOfWork
    public void testAdvancedQuery()
    {
        fail("Not yet implemented");
    }

}
