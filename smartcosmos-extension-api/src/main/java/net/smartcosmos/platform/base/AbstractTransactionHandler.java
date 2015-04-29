package net.smartcosmos.platform.base;

import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.api.transaction.ITransactionHandler;

public abstract class AbstractTransactionHandler implements ITransactionHandler
{
    protected String serviceId;

    protected String name;

    protected IContext context;

    protected AbstractTransactionHandler(String serviceId, String name)
    {
        this.serviceId = serviceId;
        this.name = name;
    }

    @Override
    public String getServiceId()
    {
        return serviceId;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setContext(IContext context)
    {
        this.context = context;
    }

    /**
     * Empty implementation.
     */
    @Override
    public void initialize()
    {

    }
}
