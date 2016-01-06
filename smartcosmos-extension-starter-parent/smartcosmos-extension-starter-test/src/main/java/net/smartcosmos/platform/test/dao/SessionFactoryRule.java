package net.smartcosmos.platform.test.dao;

import io.dropwizard.hibernate.UnitOfWork;
import net.smartcosmos.platform.jpa.integrator.PlatformHibernateIntegrator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.integrator.spi.IntegratorService;
import org.hibernate.service.ServiceRegistry;
import org.hsqldb.jdbcDriver;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This enables adding a Rule to your JUnit tests so you can use the familiar {@link UnitOfWork} on the test methods
 * instead of dealing with {@link javax.transaction.Transactional}.
 * 
 * @author voor
 *
 */
public class SessionFactoryRule implements MethodRule
{

    /**
     * Logging.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SessionFactoryRule.class);

    private final SessionFactory sessionFactory;

    public SessionFactoryRule(final SessionFactory sessionFactory)
    {
        super();
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Override
    public Statement apply(final Statement statement, final FrameworkMethod method, final Object target)
    {
        final UnitOfWork unitOfWork = method.getAnnotation(UnitOfWork.class);
        if (unitOfWork != null)
        {
            return new UnitOfWorkTestStatement(unitOfWork, statement, sessionFactory);
        } else
        {
            return statement;
        }
    }

    /**
     * Bootstrapping process for creating the session factory, making sure the Hibernate Integrator is present.
     * 
     * @see PlatformHibernateIntegrator
     * @see org.hibernate.context.internal.ManagedSessionContext
     *
     * @param entities
     * @return created in-memory session factory.
     */
    public static SessionFactoryRule build(final List<Class<?>> entities)
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

            if (entities != null)
            {
                for (Class<?> clazz : entities)
                {
                    configuration.addAnnotatedClass(clazz);
                }
            }

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

            return new SessionFactoryRule(sessionFactory);
        } catch (Exception ex)
        {
            LOG.error("Initial SessionFactory creation failed." + ex);
            LOG.debug(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
