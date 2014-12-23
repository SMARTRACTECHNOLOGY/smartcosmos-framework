package net.smartcosmos.client.objects.object;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.endpoint.ObjectEndpoints;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

class ObjectClient extends AbstractUpdateableBaseClient<IObject> implements IObjectClient
{
    final static Logger LOGGER = LoggerFactory.getLogger(ObjectClient.class);

    ObjectClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IObject findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ObjectEndpoints.findByUrn(urn, viewType), ObjectImpl.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, ObjectEndpoints.create());
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, ObjectEndpoints.update());
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn) throws ServiceException
    {
        return findByExactObjectUrn(objectUrn, ViewType.Standard);
    }

    @Override
    public IObject findByExactObjectUrn(String objectUrn, ViewType viewType) throws ServiceException
    {
        GetCommand<IObject> command = new GetCommand<>(context);
        return command.call(ObjectImpl.class, ObjectEndpoints.findByExactObjectUrn(objectUrn, viewType));
    }

    @Override
    public Collection<IObject> query(ObjectEndpoints.Builder builder) throws ServiceException
    {
        GetCollectionCommand<IObject> command = new GetCollectionCommand<>(context);
        return command.call(ObjectImpl.class, builder.build());
    }
}
