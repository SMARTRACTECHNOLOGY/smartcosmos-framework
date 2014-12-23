package net.smartcosmos.client.objects.geospatial;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpdateableBaseClient;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.endpoint.GeospatialEndpoints;
import net.smartcosmos.model.geo.IGeospatialEntry;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.geo.GeospatialEntry;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONObject;

import java.util.Collection;

class GeospatialClient extends AbstractUpdateableBaseClient<IGeospatialEntry> implements IGeospatialClient
{
    GeospatialClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public void update(JSONObject instance) throws ServiceException
    {
        update(instance, GeospatialEndpoints.update());
    }

    @Override
    public IGeospatialEntry findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, GeospatialEndpoints.findByUrn(urn, viewType), GeospatialEntry.class);
    }

    @Override
    public ResponseEntity create(JSONObject instance) throws ServiceException
    {
        return create(instance, GeospatialEndpoints.create());
    }

    @Override
    public Collection<IGeospatialEntry> findByNameLike(String nameLike, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<IGeospatialEntry> command = new GetCollectionCommand<>(context);
        return command.call(GeospatialEntry.class, GeospatialEndpoints.findByNameLike(nameLike, viewType));
    }

    @Override
    public Collection<IGeospatialEntry> findByNameLike(String nameLike) throws ServiceException
    {
        return findByNameLike(nameLike, ViewType.Standard);
    }
}
