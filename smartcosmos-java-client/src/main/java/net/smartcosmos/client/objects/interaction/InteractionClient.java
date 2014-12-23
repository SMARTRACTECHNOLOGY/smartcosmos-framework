package net.smartcosmos.client.objects.interaction;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractCreateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.InteractionEndpoints;
import net.smartcosmos.objects.model.context.IObjectInteraction;
import net.smartcosmos.objects.pojo.context.ObjectInteraction;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class InteractionClient extends AbstractCreateableBaseClient<IObjectInteraction> implements IInteractionClient
{
    InteractionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public IObjectInteraction findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, InteractionEndpoints.findByUrn(urn, viewType), ObjectInteraction.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, InteractionEndpoints.create());
    }

    @Override
    public Collection<IObjectInteraction> listAll(ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteraction.class, InteractionEndpoints.listAllInteractions(viewType));
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrn(String objectUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteraction> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteraction.class, InteractionEndpoints.findByObjectUrn(objectUrn, viewType));
    }

    @Override
    public Collection<IObjectInteraction> listAll() throws ServiceException
    {
        return listAll(ViewType.Standard);
    }

    @Override
    public Collection<IObjectInteraction> findByObjectUrn(String objectUrn) throws ServiceException
    {
        return findByObjectUrn(objectUrn, ViewType.Standard);
    }
}
