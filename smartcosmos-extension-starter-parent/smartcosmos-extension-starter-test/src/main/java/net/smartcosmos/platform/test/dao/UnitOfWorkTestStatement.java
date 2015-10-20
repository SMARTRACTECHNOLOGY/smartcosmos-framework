package net.smartcosmos.platform.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * This is a straight copy pasta from {@link io.dropwizard.hibernate.UnitOfWorkRequestDispatcher} in order to facilitate
 * using {@link UnitOfWork} inside JUnit tests instead of HTTP Requests.
 * 
 * @see SessionFactoryRule
 * 
 * @author voor
 *
 */
public class UnitOfWorkTestStatement extends Statement
{

    private static final Logger LOG = LoggerFactory.getLogger(UnitOfWorkTestStatement.class);

    private final UnitOfWork unitOfWork;
    private final Statement statement;
    private final SessionFactory sessionFactory;

    public UnitOfWorkTestStatement(final UnitOfWork unitOfWork,
            final Statement statement,
            final SessionFactory sessionFactory)
    {
        this.unitOfWork = unitOfWork;
        this.statement = statement;
        this.sessionFactory = sessionFactory;
    }

    public UnitOfWork getUnitOfWork()
    {
        return unitOfWork;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    private void beginTransaction(final Session session)
    {
        if (unitOfWork.transactional())
        {
            session.beginTransaction();
        }
    }

    private void configureSession(final Session session)
    {
        session.setDefaultReadOnly(unitOfWork.readOnly());
        session.setCacheMode(unitOfWork.cacheMode());
        session.setFlushMode(unitOfWork.flushMode());
    }

    private void rollbackTransaction(final Session session)
    {
        if (unitOfWork.transactional())
        {
            final Transaction txn = session.getTransaction();
            if (txn != null && txn.isActive())
            {
                txn.rollback();
            }
        }
    }

    private void commitTransaction(final Session session)
    {
        if (unitOfWork.transactional())
        {
            final Transaction txn = session.getTransaction();
            if (txn != null && txn.isActive())
            {
                txn.commit();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends Exception> void rethrow(final Exception e) throws E
    {
        throw (E) e;
    }

    @Override
    public void evaluate() throws Throwable
    {
        LOG.debug("Starting UnitOfWork session.");
        final Session session = sessionFactory.openSession();
        try
        {
            configureSession(session);
            LOG.trace("Session configured.");
            ManagedSessionContext.bind(session);
            beginTransaction(session);
            LOG.trace("Transaction started.");
            try
            {
                statement.evaluate();
                commitTransaction(session);
            } catch (Exception e)
            {
                rollbackTransaction(session);
                this.<RuntimeException> rethrow(e);
            }
            LOG.trace("Evaluation concluded.");
        } finally
        {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
    }

}
