package net.smartcosmos.client.impl.base;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IFindableBaseClient;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.util.json.ViewType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFindableBaseClient<T> extends AbstractBaseClient implements IFindableBaseClient<T>
{
    final static Logger LOGGER = LoggerFactory.getLogger(AbstractFindableBaseClient.class);

    protected AbstractFindableBaseClient(ServerContext context)
    {
        super(context);
    }

    public T findByUrn(String urn) throws ServiceException
    {
        return findByUrn(urn, ViewType.Standard);
    }

    protected T findByUrn(String urn, String path, Class<? extends T> clazz) throws ServiceException
    {
        GetCommand<T> command = new GetCommand<>(context);
        return command.call(clazz, path);
    }
}
