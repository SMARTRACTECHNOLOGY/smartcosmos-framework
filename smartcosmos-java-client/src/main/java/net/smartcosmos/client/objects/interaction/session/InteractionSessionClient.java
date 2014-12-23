package net.smartcosmos.client.objects.interaction.session;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.endpoint.ObjectInteractionSessionEndpoints;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.objects.pojo.context.ObjectInteractionSession;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class InteractionSessionClient extends AbstractUpdateableBaseClient<IObjectInteractionSession> implements IInteractionSessionClient
{
    InteractionSessionClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public Collection<IObjectInteractionSession> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IObjectInteractionSession> command = new GetCollectionCommand<>(context);
        return command.call(ObjectInteractionSession.class, ObjectInteractionSessionEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, ObjectInteractionSessionEndpoints.update());
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, ObjectInteractionSessionEndpoints.create());
    }

    @Override
    public IObjectInteractionSession findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, ObjectInteractionSessionEndpoints.findByUrn(urn, viewType), ObjectInteractionSession.class);
    }

    @Override
    public Collection<IObjectInteractionSession> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
