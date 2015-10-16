package net.smartcosmos.platform.test.dao;

import org.hibernate.SessionFactory;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * This enables adding a Rule to your JUnit tests so you can use the familiar {@link UnitOfWork} on the test methods
 * instead of dealing with {@link javax.transaction.Transactional}.
 * 
 * @author voor
 *
 */
public class SessionFactoryRule implements MethodRule
{

    private final SessionFactory sessionFactory;

    public SessionFactoryRule(SessionFactory sessionFactory)
    {
        super();
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Override
    public Statement apply(Statement statement, FrameworkMethod method, Object target)
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

}
